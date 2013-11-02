package com.comet.core.orm.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.SessionImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.comet.core.utils.BeanUtils;

/**
 * Hibernate的范型基类.
 * <p/>
 * 可以在service类中直接创建使用.也可以继承出DAO子类,在多个Service类中共享DAO操作.
 * 参考Spring2.5自带的Petlinc例子,取消了HibernateTemplate.
 * 通过Hibernate的sessionFactory.getCurrentSession()获得session,直接使用Hibernate原生API.
 *
 * @author Wang Kaifeng
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 */
@SuppressWarnings("unchecked")
public class SimpleHibernateTemplate<T, PK extends Serializable> {
    private static Logger log = LoggerFactory.getLogger(SimpleHibernateTemplate.class);

    protected SessionFactory sessionFactory;

    protected Class<T> entityClass;

    public SimpleHibernateTemplate(SessionFactory sessionFactory,
                                   Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    public void evict(T entity) {
        getSession().evict(entity);
    }

    public void evict() {
        getSessionFactory().evict(entityClass);
    }

    public Filter enableFilter(String filterName) {
        return getSession().enableFilter(filterName);
    }

    public void disableFilter(String filterName) {
        getSession().disableFilter(filterName);
    }

    public void save(T entity) {
        Assert.notNull(entity);
        getSession().saveOrUpdate(entity);
        log.info("save entity: " + entity);
    }

    public void insert(T entity) {
        Assert.notNull(entity);
        getSession().save(entity);
        log.info("insert entity: " + entity);
    }

    public void update(T entity) {
        Assert.notNull(entity);
        getSession().update(entity);
        log.info("update entity: " + entity);
    }

    public void delete(T entity) {
        Assert.notNull(entity);
        getSession().delete(entity);
        log.info("delete entity: {}" + entity);
    }

    public void delete(PK id) {
        Assert.notNull(id);
        delete(get(id));
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    public Page<T> findAll(Page<T> page) {
        return findByCriteria(page);
    }

    /**
     * 按id获取对象.
     */
    public T get(final PK id) {
        return (T) getSession().load(entityClass, id);
    }

    /**
     * Load the data for the object with the specified id into a newly created object.
     * This is only called when lazily initializing a proxy.
     * Do NOT return a proxy.
     */
    public T immediateLoad(final PK id) {
        return (T)((SessionImpl)getSession()).immediateLoad(
                entityClass.getName(), id);
    }

    /**
     * 按HQL查询对象列表.
     *
     * @param hql
     *            hql语句
     * @param values
     *            数量可变的参数
     */
    public List<T> find(String hql, Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 按HQL分页查询. 暂不支持自动获取总结果数,需用户另行执行查询.
     *
     * @param page
     *            分页参数.包括pageSize 和firstResult.
     * @param hql
     *            hql语句.
     * @param values
     *            数量可变的参数.
     * @return 分页查询结果,附带结果列表及所有查询时的参数.
     */
    public Page<T> find(Page<T> page, String hql, Object... values) {
        Assert.notNull(page);

        if (page.isAutoCount()) {
            String lowerHql = hql.trim().toLowerCase();
            int fromPos = 0;
            if (!lowerHql.startsWith("from ")) {
                fromPos = lowerHql.indexOf(" from ") + 1;
            }

            String countHql;
            if (fromPos == 0) {
                countHql = "select count(*) " + hql;
            } else {
                countHql = "select count(*) " + hql.substring(fromPos);
            }
            int totalCount;
            Query countQuery = createQuery(countHql);
            totalCount = ((Long) (countQuery.uniqueResult())).intValue();
            page.setRecords(totalCount);
            log.debug("totalCount=" + totalCount);
            // logger.warn("HQL查询暂不支持自动获取总结果数,hql为{}" + hql);
        }
        Query q = createQuery(hql, values);
        if (page.isFirstSetted()) {
            q.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            q.setMaxResults(page.getPageSize());
        }
        page.setRows(q.list());
        return page;
    }

    /**
     *
     * @param page
     * @param countSql
     * @param hql
     * @param values
     * @return
     */
    public Page<T> find(Page<T> page, String countSql,String hql, Object... values) {
        Assert.notNull(page);

        if (page.isAutoCount()) {
            String lowerHql = countSql.trim().toLowerCase();
            int fromPos = 0;
            if (!lowerHql.startsWith("from ")) {
                fromPos = lowerHql.indexOf(" from ") + 1;
            }

            if (fromPos == 0) {
                countSql = "select count(*) " + countSql;
            } else {
                countSql = "select count(*) " + countSql.substring(fromPos);
            }
            int totalCount;
            Query countQuery = createSQLQuery(countSql);
            totalCount = Integer.valueOf(countQuery.uniqueResult().toString());
            page.setRecords(totalCount);
            log.debug("totalCount=" + totalCount);
//            logger.warn("HQL查询暂不支持自动获取总结果数,hql为{}" + hql);
        }
        return findbyPage(page, hql, values);
    }

    /**
     *
     * @param page
     * @param hql
     * @param values
     * @return
     */
    private Page<T> findbyPage(Page<T> page, String hql, Object... values) {
        Query q = createQuery(hql, values);
        if (page.isFirstSetted()) {
            q.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            q.setMaxResults(page.getPageSize());
        }
        page.setRows(q.list());
        return page;
    }


    /**
     * 按HQL查询唯一对象.
     */
    public Object findUnique(String hql, Object... values) {
        return createQuery(hql, values).uniqueResult();
    }

    /**
     * 按HQL查询Intger类形结果.
     */
    public Integer findInt(String hql, Object... values) {
        return (Integer) findUnique(hql, values);
    }

    /**
     * 按HQL查询Long类型结果.
     */
    public Long findLong(String hql, Object... values) {
        return (Long) findUnique(hql, values);
    }

    /**
     * 按Criterion查询对象列表.
     *
     * @param criterion
     *            数量可变的Criterion.
     */
    public List<T> findByCriteria(Criterion... criterion) {
        return createCriteria(criterion).list();
    }

    /**
     * 按Criterion分页查询.
     *
     * @param page
     *            分页参数.包括pageSize、firstResult、orderBy、asc、autoCount.
     *            其中firstResult可直接指定,也可以指定pageNo. autoCount指定是否动态获取总结果数.
     * @param criterion
     *            数量可变的Criterion.
     * @return 分页查询结果.附带结果列表及所有查询时的参数.
     */
    public Page<T> findByCriteria(Page page, Criterion... criterion) {
        Assert.notNull(page);

        Criteria c = createCriteria(criterion);

        if (page.isAutoCount()) {
            page.setRecords(countQueryResult(page, c));
        }
        if (page.isFirstSetted()) {
            c.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            c.setMaxResults(page.getPageSize());
        }

        if (page.isOrderBySetted()) {
            if (page.getOrder().endsWith(QueryParameter.ASC)) {
                c.addOrder(Order.asc(page.getOrderBy()));
            } else {
                c.addOrder(Order.desc(page.getOrderBy()));
            }
        }
        if (page.isAutoCount() && -1 == page.getRecords()) {
            page.setRows(new ArrayList());
        } else {
            page.setRows(c.list());
        }
        return page;
    }

    /**
     * 按属性查找对象列表.
     */
    public List<T> findByProperty(String propertyName, Object value) {
        Assert.hasText(propertyName);
        Criteria q = createCriteria(Restrictions.eq(propertyName, value));

        return q.list();
    }

    /**
     * 按属性查找唯一对象.
     */
    public T findUniqueByProperty(String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) createCriteria(Restrictions.eq(propertyName, value))
                .uniqueResult();
    }

    /**
     * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
     */
    public Query createQuery(String queryString, Object... values) {
        try {
            Assert.hasText(queryString);
            Query queryObject = getSession().createQuery(queryString);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    queryObject.setParameter(i, values[i]);
                }
            }
            return queryObject;
        } catch (HibernateException e) {
            log.info(queryString);
            log.error("error", e);
            return null;
        }
    }

    /**
     * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
     */
    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 根据DetachedCriteria来查询结果.
     */
    public List findByDetachedCriteria(final DetachedCriteria criteria) {
//        Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
        Session session = null;

        try {
            session = getSessionFactory().openSession();
            Criteria executableCriteria = criteria.getExecutableCriteria(session);

            return executableCriteria.list();
        } catch (HibernateException e) {
            log.error("HibernateException", e);

            throw e;
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    /**
     * 判断对象的属性值在数据库内是否唯一.
     * <p/>
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原值(orgValue)则不作比较.
     * 传回orgValue的设计侧重于从页面上发出Ajax判断请求的场景. 否则需要SS2里那种以对象ID作为第3个参数的isUnique函数.
     */
    public boolean isPropertyUnique(String propertyName, Object newValue,
                                    Object orgValue) {
        if (newValue == null || newValue.equals(orgValue))
            return true;

        Object object = findUniqueByProperty(propertyName, newValue);
        return (object == null);
    }

    /**
     * 通过count查询获得本次查询所能获得的对象总数.
     *
     * @return page对象中的totalCount属性将赋值.
     */
    protected int countQueryResult(Page<T> page, Criteria c) {
        CriteriaImpl impl = (CriteriaImpl) c;

        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();

        List<CriteriaImpl.OrderEntry> orderEntries = null;
        try {
            orderEntries = (List) BeanUtils.getFieldValue(impl, "orderEntries");
            BeanUtils.setFieldValue(impl, "orderEntries", new ArrayList());
        } catch (Exception e) {
            log.error("不可能抛出的异常:{}" + e.getMessage());
        }

        // 执行Count查询
        int totalCount = (Integer) c.setProjection(Projections.rowCount())
                .uniqueResult();
        if (totalCount < 1) {
            return -1;
        }

        // 将之前的Projection和OrderBy条件重新设回去
        c.setProjection(projection);

        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null) {
            c.setResultTransformer(transformer);
        }

        try {
            BeanUtils.setFieldValue(impl, "orderEntries", orderEntries);
        } catch (Exception e) {
            log.error("不可能抛出的异常:{}" + e.getMessage());
        }

        return totalCount;
    }

    /**
     * 按属性查找对象分页
     *
     * @param page
     * @param propertyName
     * @param value
     * @return
     */
    public Page<T> findByPropety(Page<T> page, String propertyName, Object value) {
        Assert.notNull(page);
        Assert.hasText(propertyName);

        Criterion criterion = Restrictions.eq(propertyName, value);
        return findByCriteria(page, criterion);
    }

    /**
     * 按照SQL进行查询
     *
     * @param page
     * @param sql
     * @param values
     * @return
     */
    public Page<T> findBySql(final Page<T> page, final String sql,
                             Object[] entity, final Object... values) {
        Assert.notNull(page);

        if (page.isAutoCount()) {
            // logger.warn("HQL查询暂不支持自动获取总结果数,hql语句为{}", sql);
            page.setRecords(this.getResultCountSql(sql, values));
        }
        SQLQuery q = createSQLQuery(sql, values);

        if (entity != null && entity.length > 0) {
            for (Object obj : entity) {
                q.addEntity(obj.getClass());
            }
        }

        if (page.isFirstSetted()) {
            q.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            q.setMaxResults(page.getPageSize());
        }

        page.setRows(q.list());
        return page;
    }

    /**
     * 取得查询结果统计
     *
     * @param queryString
     * @param args
     * @return
     */
    public int getResultCountSql(String queryString, Object args[]) {
        String hqlstr = removeOrders(queryString);
        if (hasDistinctOrGroupBy(hqlstr)) {
            List lt = findBySql(hqlstr, args);
            return lt != null ? lt.size() : 0;
        }
        // String countQueryString = " select count (*) " + removeSelect(removeOrders(queryString));

        String countQueryString = " select count(*) from ( " + removeOrders(queryString) + ") as sqlCount";
        List countlist = findBySql(countQueryString, args);
        int count = 0;
        if (countlist.size() == 1)
            count = ((java.math.BigInteger) countlist.get(0)).intValue();
        else
            count = countlist.size();
        return count;
    }

    /**
     * 按照SQL进行查询
     *
     * @param sql
     * @param values
     * @return
     */
    public List findBySql(final String sql, final Object... values) {
        SQLQuery q = createSQLQuery(sql, values);

        return q.list();
    }

    /**
     * 移除Order 子句
     *
     * @param hql
     * @return
     */
    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        for (; m.find(); m.appendReplacement(sb, ""))
            ;
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 根据查询SQL与参数列表创建SQLQuery对象,辅助函数.
     */
    public SQLQuery createSQLQuery(final String queryString,
                                   final Object... values) {
        Assert.hasText(queryString);
        SQLQuery queryObject = getSession().createSQLQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }

    /**
     * 判断是否有distinct或者Group by关键字
     * @param str
     * @return
     */
    private boolean hasDistinctOrGroupBy(String str) {
        Pattern p = Pattern.compile("group\\s*by[\\w|\\W|\\s|\\S]*", 2);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        } else {
            p = Pattern.compile("distinct ", 2);
            m = p.matcher(str);
            return m.find();
        }
    }
}