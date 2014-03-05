package com.article.util;

import java.io.File;
import java.math.BigDecimal;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class PPTOperator {

    public static final String [] KV = {"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public String[] labels = new String[]{"沈阳","鞍山","大连","锦州","葫芦岛","盘锦","朝阳","铁岭","本溪","抚顺","营口","赤峰"};
    public BigDecimal[] values = new BigDecimal[]{
            new BigDecimal("100"),
            new BigDecimal("800"),
            new BigDecimal("1500"),
            new BigDecimal("3500"),
            new BigDecimal("2500"),
            new BigDecimal("4500"),
            new BigDecimal("2000"),
            new BigDecimal("3000"),
            new BigDecimal("1800"),
            new BigDecimal("2100"),
            new BigDecimal("2100"),
            new BigDecimal("1900")};

    public String WORKBOOK_NAME;

    public String WORKSHEET_NAME;

    public int CHART_TYPE;

    public static final int WORD_HTML = 8;

    public static final int WORD_TXT = 7;

    public static final int EXCEL_HTML = 44;

    public static final int ppSaveAsJPG = 17;

    private static final String ADD_CHART = "AddChart";

    private ActiveXComponent ppt;
    private ActiveXComponent presentation;

    public PPTOperator( String filePath ,boolean isVisble )throws Exception{
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }

        ppt = new ActiveXComponent("PowerPoint.Application");
        setIsVisble(ppt, isVisble);
        // 打开一个现有的 Presentation 对象
        ActiveXComponent presentations = ppt.getPropertyAsComponent("Presentations");
        presentation = presentations.invokeGetComponent("Open", new Variant(file.getAbsolutePath()), new Variant(false));
    }
    /**
     * 将ppt转化为图片
     *
     * @param pptfile
     * @param saveToFolder
     */
    public void PPTToJPG( String saveToFolder) {
        try {
            saveAs(presentation, saveToFolder, ppSaveAsJPG);
            if (presentation != null) {
                Dispatch.call(presentation, "Close");
                presentation = null;
            }
        } catch (Exception e) {
            presentation = null;
            ComThread.Release();
        }finally {
            if (presentation != null) {
                Dispatch.call(presentation, "Close");
            }
            ppt.invoke("Quit", new Variant[] {});
            ComThread.Release();
        }
    }
    /**
     * 播放ppt
     *
     * @param pptFile
     */
    public void PPTShow(String pptFile) {
        // powerpoint幻灯展示设置对象
        ActiveXComponent setting = presentation
                .getPropertyAsComponent("SlideShowSettings");
        // 调用该对象的run函数实现全屏播放
        setting.invoke("Run");
        // 释放控制线程
        ComThread.Release();
    }

    /**
     * ppt另存为
     *
     * @param presentation
     * @param saveTo
     * @param ppSaveAsFileType
     */
    public void saveAs(Dispatch presentation, String saveTo,
                       int ppSaveAsFileType)throws Exception {
        Dispatch.call(presentation, "SaveAs", saveTo, new Variant(
                ppSaveAsFileType));
    }
    /**
     * 关闭PPT并释放线程
     * @throws Exception
     */
    public void closePpt()throws Exception{
        if (null != presentation) {
            Dispatch.call(presentation, "Close");
        }
        ppt.invoke("Quit", new Variant[]{});
        ComThread.Release();
    }
    /**
     * 运行PPT
     * @throws Exception
     */
    public void runPpt()throws Exception{
        ActiveXComponent setting = presentation.getPropertyAsComponent("SlideShowSettings");
        setting.invoke("Run");
    }
    /**
     * 设置是否可见
     * @param visble
     * @param obj
     */
    private void setIsVisble(Dispatch obj,boolean visble)throws Exception{
        Dispatch.put(obj, "Visible", new Variant(visble));
    }
    /**
     * 在幻灯片对象上添加新的幻灯片
     * @param slides
     * @param pptPage 幻灯片编号
     * @param type 4:标题+表格  2:标题+文本 3:标题+左右对比文本 5:标题+左文本右图表 6:标题+左图表右文本 7:标题+SmartArt图形 8:标题+图表
     * @return
     * @throws Exception
     */
    private Variant addPptPage(ActiveXComponent slides,int pptPage,int type)throws Exception{
        return slides.invoke("Add", new Variant(pptPage), new Variant(type));
    }

    /**
     *
     * @param pageShapes 页面的SHAPES的对象
     * @param chartType 图表类型
     * @param width 图表的宽度
     * @param height 图表的高度
     * @return
     * @throws Exception
     */
    public Dispatch addChart(Dispatch pageShapes,int chartType,int left,int top,int width,int height)throws Exception{

        String METHOD_CHART = "AddChart";

        Variant chart = Dispatch.invoke(pageShapes, METHOD_CHART, 1, new Object[]{
                new Integer(chartType),//图表类型
                new Integer(left),//距离左边框的距离
                new Integer(top),//距离上边框的距离
                new Integer(width),//图表的宽度
                new Integer(height),//图表的高度
        },
                new int[1]);//错误类型

        Dispatch chartObj=Dispatch.get( chart.toDispatch(), "Chart").getDispatch();

        this.getSheet(chartObj);

        return chart.toDispatch();
    }
    /**
     * 获取第几个幻灯片
     * @return
     * @throws Exception
     */
    public Dispatch getPptPage(int pageIndex)throws Exception{
        //获取幻灯片对象
        ActiveXComponent slides = presentation.getPropertyAsComponent("Slides");
        //获得第几个PPT
        Dispatch pptPage = Dispatch.call(slides, "Item", new Object[]{new Variant(pageIndex)}).toDispatch();
        Dispatch.call(pptPage, "Select");
        return pptPage;
    }
    /**
     * 添加表格
     * @param pageShapes 页面对象
     * @param rows 行数
     * @param columns 列数
     * @param leftDistance 距离左边距离
     * @param topDistance 距离顶部距离
     * @param width 宽度
     * @param height 高度
     * @return
     * @throws Exception
     */
    public Dispatch addTable(Dispatch pageShapes, long rows,
                             long columns, int leftDistance, int topDistance, int width,
                             int height) throws Exception {
        return Dispatch.invoke(
                pageShapes,
                "AddTable",
                1,
                new Object[] { new Long(rows), new Long(columns),
                        new Integer(leftDistance), new Integer(topDistance),
                        new Integer(width), new Integer(height) }, new int[1])
                .toDispatch();
    }
    /**
     * 在Selection对象上修改TEXT对象的值
     * @param selectionObj
     * @param value
     * @throws Exception
     */
    public void addTextValue(Dispatch selectionObj,String value)throws Exception{
        Dispatch shapeRange=(Dispatch)Dispatch.get(selectionObj, "ShapeRange").getDispatch();
        Dispatch textFrame=(Dispatch)Dispatch.get(shapeRange, "TextFrame").getDispatch();
        Dispatch textRange=(Dispatch)Dispatch.get(textFrame, "TextRange").getDispatch();
        Dispatch.call(textRange, "Select");
        Dispatch.put(textRange,"Text",value);
    }
    /**
     * 将数据添加到制定的单元格内
     * @param cell 单元格对象
     * @param value 需要添加的数据
     * @throws Exception
     */
    public void addCellValue(Dispatch cell,Object value)throws Exception{
        Dispatch cellShape = Dispatch.get(cell, "Shape").toDispatch();
        Dispatch cellFrame = Dispatch.get(cellShape, "TextFrame").toDispatch();
        Dispatch cellRange = Dispatch.get(cellFrame, "TextRange").toDispatch();
        Dispatch.put(cellRange, "Text", value);
    }
    /**
     * 合并单元格,合并之后原来两个单元格的内容将放到一个单元格里面
     * 如果开始单元格和结束单元之间跨几个单元格，将会一起被合并
     * @param cell 开始单元格
     * @param cell2 结束单元格
     * @return
     * @throws Exception
     */
    public void mergeCell(Dispatch cell,Dispatch cell2)throws Exception{
        Dispatch.invoke(cell, "Merge", 1, new Object[]{cell2}, new int[1]);
    }
    /**
     * 获取表格的制定单元格
     * @param tableObj 表格对象
     * @param rowNum 第几行，从1开始
     * @param columnRum 第几列，从1开始
     * @return
     * @throws Exception
     */
    public Dispatch getCellOfTable(Dispatch tableObj,int rowNum,int columnRum)throws Exception{
        return Dispatch.invoke(tableObj, "Cell", Dispatch.Method, new Object[]{new Long(rowNum),new Long(columnRum)}, new int[1]).toDispatch();
    }
    /**
     * 设置单元格背景颜色
     * @param cellObj
     * @param colorIndex
     * @throws Exception
     */
    public void setCellBackColor(Dispatch cellObj,int colorIndex)throws Exception{
        Dispatch cellShape = Dispatch.get(cellObj, "Shape").toDispatch();
        Dispatch fillObj = Dispatch.get(cellShape, "Fill").toDispatch();
        Dispatch backColor = Dispatch.get(fillObj, "ForeColor").toDispatch();
        Dispatch.put(backColor, "ObjectThemeColor", colorIndex);
        Dispatch.put(fillObj, "ForeColor", backColor);
    }
    /**
     * 修改表格的样式，默认样式为:{5C22544A-7EE6-4342-B048-85BDC9FD1C3A}
     * @param tableObj 表格对象
     * @param styleId 样式ID
     * @throws Exception
     */
    public void editTableSyle(Dispatch tableObj,String styleId)throws Exception{
        if(null == tableObj){
            throw new Exception("无效的表格对象!");
        }
        if(null == styleId || "".equals(styleId)){
            throw new Exception("无效的样式ID!");
        }
        Dispatch.invoke(tableObj, "ApplyStyle", Dispatch.Method, new Object[]{styleId}, new int[1]);
    }
    /**
     * 在TABLE对象上添加列
     * @param tableObj
     * @param beforeColumn
     * @throws Exception
     */
    public void addTableColumn(Dispatch tableObj,int beforeColumn)throws Exception{
        Dispatch columns = Dispatch.get(tableObj, "Columns").getDispatch();
        int count = Dispatch.get(columns, "Count").getInt();
        if(beforeColumn > count || beforeColumn < 1){
            throw new Exception("无效的列索引!");
        }
        Dispatch.invoke(columns, "Add", Dispatch.Method, new Object[]{beforeColumn}, new int[1]);
    }
    /**
     * 在TABLE对象上添加行
     * @param tableObj
     * @param beforeColumn
     * @throws Exception
     */
    public void addTableRow(Dispatch tableObj,int beforeRow)throws Exception{
        Dispatch rows = Dispatch.get(tableObj, "Rows").getDispatch();
        int count = Dispatch.get(rows, "Count").getInt();
        if(beforeRow > count || beforeRow <1){
            throw new Exception("无效的行索引!");
        }
        Dispatch.invoke(rows, "Add", Dispatch.Method, new Object[]{beforeRow}, new int[1]);
    }
    /**
     * 修改单个些列的图表类型
     * @param chartObj 图表对象
     * @param seriIndex 系列索引，从1开始
     * @param chartType 图表类型
     * @throws Exception
     */
    public void updateSeriChartType(Dispatch chartObj,int seriIndex,int chartType)throws Exception{
        Dispatch Seri1 = Dispatch.call(chartObj, "SeriesCollection", new Variant(seriIndex)).toDispatch();
        Dispatch.put(Seri1,"ChartType",chartType);
    }
    /**
     * 设置是否显示图表的数据表格,当新增一个表格时默认时不显示的
     * @param chartObj 表格对象
     * @param bValue 布尔值，ture为显示，false为不显示
     * @throws Exception
     */
    public void setIsDispDataTable(Dispatch chartObj,boolean bValue)throws Exception{
        if(null == chartObj){
            throw new Exception("无效的图表对象!");
        }
        Dispatch.put(chartObj, "HasDataTable", bValue);
    }
    /**
     * 获取表格的样式ID
     * @param tableObj
     * @return
     * @throws Exception
     */
    public String getTableStyleId(Dispatch tableObj)throws Exception{
        Dispatch tableStyle = Dispatch.get(tableObj, "Style").toDispatch();
        return Dispatch.get(tableStyle, "Id").toString();
    }
    /**
     * 设置图表上是否显示数据表格
     * @param chartObj
     * @param value
     * @throws Exception
     */
    public void setHasDataTable(Dispatch chartObj,boolean value)throws Exception{
        Dispatch.put(chartObj, "HasDataTable", value);
    }

    public void getGeneragePpt() throws Exception {
        // 生成一个新的ppt 对象
        Dispatch windows = presentation.getProperty("Windows").toDispatch();
        Dispatch window = Dispatch.call(windows, "Item", new Variant(1)).toDispatch();
        Dispatch selection = Dispatch.get(window, "Selection").toDispatch();
        //获取幻灯片对象
        ActiveXComponent slides = presentation.getPropertyAsComponent("Slides");

        //添加第一张幻灯片； 标题+副标题
        Variant v = addPptPage(slides, 1, 8);

        Dispatch slideRange=(Dispatch)Dispatch.get(selection, "SlideRange").getDispatch();
        Dispatch shapes=(Dispatch)Dispatch.get(slideRange, "Shapes").getDispatch();
        //获取幻灯片中的第一个元素
        Dispatch shape1 = Dispatch.call(shapes, "Item", new Variant(1)).toDispatch();
        //获取幻灯片中的第二个元素
        Dispatch shape2 = Dispatch.call(shapes, "Item", new Variant(2)).toDispatch();
        //选中第一个元素
        Dispatch.call(shape1, "Select");
        //添加值
        addTextValue(selection, "测试主标题");
        //操作PPT一页中的第二个shape
        Dispatch.call(shape2, "Select");
        addTextValue(selection, "测试副标题");
        //添加第二张幻灯片(标题+图表)
//        Variant v = addPptPage(slides, 2, 8);
        //获取第二个PPT对象
        Dispatch pptTwo = v.getDispatch();
        //激活当前PPT对象
        Dispatch.call(pptTwo, "Select");
        //获取PPT中的shapes
        shapes = Dispatch.get(pptTwo, "Shapes").toDispatch();
        Dispatch shapeText = Dispatch.call(shapes, "Item", new Variant(1)).toDispatch();
        //操作标题
        Dispatch.call(shapeText, "Select");
        addTextValue(selection, "测试图表标题");
        //添加图表

        for(int i=1; i<3; i++){
            v = addPptPage(slides, 1+i, 8);
            pptTwo = v.getDispatch();
            Dispatch.call(pptTwo, "Select");
            shapes = Dispatch.get(pptTwo, "Shapes").toDispatch();
            addChart(shapes, i, 10, 130, 455, 228);
        }

//    addChart(shapes, 1, 10, 130, 455, 228);

        /*Dispatch range = Dispatch.invoke(sheet,
"Range",
Dispatch.Get,
new Object[] {"A2:"+KV[value.length-1]+"2","A1:"+KV[value.length-1]+"1"},
new int[2]).toDispatch();
        Dispatch.call(chartObj, "SetSourceData");*/

//        Dispatch legend = Dispatch.get(chartObj,"Legend").toDispatch();//图例 （系列1、系列2等）
//        Dispatch.call(legend,"Delete");//清除图例

        /*v = addPptPage(slides, 3, 8);
        //获取第二个PPT对象
        pptTwo = v.getDispatch();
        //激活当前PPT对象
        Dispatch.call(pptTwo, "Select");
        //获取PPT中的shapes
        shapes = Dispatch.get(pptTwo, "Shapes").toDispatch();
        chartDisp = addChart(shapes, 4, 10, 130, 700, 200);
        chartObj=Dispatch.get(chartDisp, "Chart").getDispatch();
        setHasDataTable(chartObj, false);

        sheet = this.getSheet(chartObj);
        this.setSourceData(sheet);
        legend = Dispatch.get(chartObj,"Legend").toDispatch();//图例 （系列1、系列2等）
        Dispatch.call(legend,"Delete");//清除图例*/
        //保存ppt
        presentation.invoke("SaveAs", new Variant("C:\\Users\\Administrator\\Desktop\\powerpoint.ppt"));
        // 释放控制线程
//        ComThread.Release();
        closePpt();
    }

    /**
     * 初始化数据源
     * @param Chart chartObj
     * @return WorkSheet sheet
     * @throws Exception
     */
    /**
     * @param chartObj
     * @return
     * @throws Exception
     */
    /**
     * @param chartObj
     * @return
     * @throws Exception
     */
    /**
     * @param chartObj
     * @return
     * @throws Exception
     */
    public void getSheet( Dispatch chartObj ) throws Exception{
        Dispatch chartData=Dispatch.get(chartObj, "ChartData").toDispatch();
        Dispatch workBook=Dispatch.get(chartData, "Workbook").getDispatch();

        Dispatch sheet = Dispatch.call(workBook, "ActiveSheet").toDispatch();
        this.WORKBOOK_NAME = Dispatch.get(workBook,"name").toString();
        this.WORKSHEET_NAME = Dispatch.get(sheet,"name").toString();

        int format = 1;
        int plotBy = 2;
        int chartType = Dispatch.get( chartObj, "ChartType").toInt();
        int categoryLabel = 0;
        int seriesLabel = 0;
        boolean hasLegend = false;
        String title = "title";
        String categoryTitle = "categoryTitle";
        String valueTile = "valueTile";
        String extraTitle = "extraTitle";

        //快速设置图表属性
        Dispatch.call(
                chartObj,
                "ChartWizard",
                new Variant("A1:B1"),
                new Variant(chartType),
                new Variant(format),
                new Variant(plotBy),
                new Variant(categoryLabel),
                new Variant(seriesLabel),
                new Variant(hasLegend),
                new Variant(title),
                new Variant(categoryTitle),
                new Variant(valueTile),
                new Variant(extraTitle)
        );

        //设置图表标题（true 显示；false 不显示）
        Dispatch.put(chartObj, "HasTitle", false);

        //设置图表中文字样式
        Dispatch chartFormat = Dispatch.get(chartObj, "format").toDispatch();
        Dispatch textFrame2 = Dispatch.get(chartFormat, "TextFrame2").toDispatch();
        Dispatch textRange = Dispatch.get(textFrame2, "TextRange").toDispatch();
        Dispatch font = Dispatch.get(textRange, "Font").toDispatch();
        Dispatch.put(font ,"Size" ,8);


        this.clearOldData(sheet);
        this.setSourceData(sheet);


        /*Dispatch listObjects = Dispatch.get(sheet,"ListObjects").toDispatch();
        Dispatch listObject = Dispatch.call(listObjects, "Item", new Variant(1)).toDispatch();
        Variant range = Dispatch.call(sheet, "Range", new Variant("A1:B2"));
        //调整图表数据区域的大小
        Dispatch.call(listObject, "Resize", range);*/

        //设置ppt图表的数据源
        Dispatch.call(chartObj, "SetSourceData", "="+this.WORKSHEET_NAME+"!A1:B13", new Variant(2));
        Dispatch.put(chartObj, "HasDataTable", false);
        Dispatch.call(chartObj, "Refresh");

    }

    /**
     * 清除旧数据源
     * @param sheet
     * @return Dispatch listObject
     */
    public void clearOldData(Dispatch sheet){
        Dispatch listObjects = Dispatch.get(sheet,"ListObjects").toDispatch();
        Dispatch listObject = Dispatch.call(listObjects, "Item", new Variant(1)).toDispatch();
        Dispatch ListColumns = Dispatch.get(listObject,"ListColumns").toDispatch();
        int colCount = Dispatch.get(ListColumns, "count").toInt();
        for(int i=3; i<=colCount; i++){
            Dispatch ListColumn = Dispatch.call(ListColumns, "Item", new Variant(i)).toDispatch();
            Dispatch range = Dispatch.call(ListColumn, "range").toDispatch();
            //清空range中的数据
            Dispatch.call(range,"Clear");
        }
    }

    /**
     * 设置值
     * @param sheet
     * @return
     * @throws Exception
     */
    public void setSourceData( Dispatch sheet) throws Exception{

        //添加新数据源
        for (int i = 0; i < labels.length; i++) {
            Dispatch cell = Dispatch.call(sheet, "Cells", new Integer(i+2), new Integer(1)).toDispatch();
            Dispatch.put(cell, "Value", labels[i]);
//            Dispatch.put(cell, "Name", labels[i]);
        }
        for (int i = 0; i < values.length; i++) {
            Dispatch cell = Dispatch.call(sheet, "Cells", new Integer(i+2), new Integer(2)).toDispatch();
            Dispatch.put(cell, "Value", values[i]);
//            Dispatch.put(cell, "Name", labels[i]);
        }

    }

    public static void main(String[] strs)throws Exception{
        System.out.println(System.getProperty("java.library.path"));
        PPTOperator jac = new PPTOperator("C:\\Users\\tcg\\Desktop\\53790649.ppt",true);
        jac.PPTToJPG("C:\\Users\\tcg\\Desktop\\53790649");
    }

}