﻿/**
 * jQuery jui 1.0
 *
 * http://jui.com
 *
 *
 */

(function ($)
{
    /**
     * @name   juiceDialog
     * @class   juiceDialog是属性加载结构类。
     * @constructor
     * @description 构造函数.
     * @namespace  <h3><font color="blue">juiceDialog &nbsp;API 注解说明</font></h3>
     */
    var l = $.jui;

    //全局事件
    $(".l-dialog-btn").live('mouseover', function ()
    {
        $(this).addClass("l-dialog-btn-over");
    }).live('mouseout', function ()
        {
            $(this).removeClass("l-dialog-btn-over");
        });
    $(".l-dialog-tc .l-dialog-close").live('mouseover', function ()
    {
        $(this).addClass("l-dialog-close-over");
    }).live('mouseout', function ()
        {
            $(this).removeClass("l-dialog-close-over");
        });


    $.juiceDialog = function ()
    {
        return l.run.call(null, "juiceDialog", arguments, { isStatic: true });
    };

    //dialog 图片文件夹的路径 预加载
    $.jui.DialogImagePath = "../../js/jui/skins/Aqua/images/win/";

    function prevImage(paths)
    {
        for (var i in paths)
        {
            $('<img />').attr('src', l.DialogImagePath + paths[i]);
        }
    }
    //prevImage(['dialog.gif', 'dialog-winbtns.gif', 'dialog-bc.gif', 'dialog-tc.gif']);

    $.juiceDefaults.Dialog = /**@lends juiceDialog#*/  {
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 给dialog附加css样式。
         * @default null
         * @type Object
         */
        cls: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 给dialog附加id。
         * @default null
         * @type String
         */
        id: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 按钮集合。
         * @default null
         * @type Object
         */
        buttons: null,
        /**
         * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按钮显示位置 ，默认右对齐
         * @default right
         * @type String
         */
        buttonAlign:"right",
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否拖动,为true表示拖动，false表示不拖动。
         * @default true
         * @type Boolean
         */
        isDrag: true,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 宽度。
         * @default 360
         * @type  Number
         */
        width: 360,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 高度。
         * @default 80
         * @type  Number
         */
        height: 80,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 内容。
         * @default ''
         * @type  String
         */
        content: '',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 目标对象，指定它将以appendTo()的方式载入。
         * @default null
         * @type  Object
         */
        target: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 目标页url，默认以iframe的方式载入。
         * @default null
         * @type  String
         */
        url: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否以load()的方式加载目标页的内容。
         * @default false
         * @type  Boolean
         */
        load: false,
        onLoaded: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 类型 warn、success、error、question。
         * @default none
         * @type  String
         */
        type: 'none',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 位置left。
         * @default 0
         * @type  Number
         */
        left: 0,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 位置top。
         * @default 0
         * @type  Number
         */
        top: 0,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否模态对话框,为true时表示是，为false则不是。
         * @default true
         * @type  Boolean
         */
        modal: true,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 创建iframe时 作为iframe的name和id 。
         * @default null
         * @type  String
         */
        name: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否调整大小,为true时表示是，为false则不是。
         * @default false
         * @type  Boolean
         */
        isResize: false,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否允许关闭,为true时表示允许，为false则不允许。
         * @default false
         * @type  Boolean
         */
        allowClose: true,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 打开一个子窗口。
         * @default null
         * @type  Object
         */
        opener: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否给URL后面加上值为new Date().getTime()的参数，如果需要指定一个参数名即可。
         * @default null
         * @type  Object
         */
        timeParmName: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 回车时是否关闭dialog,为true时关闭，false时不关闭。
         * @default false
         * @type  Boolean
         */
        closeWhenEnter: false,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 关闭对话框时是否只是隐藏，还是销毁对话框。
         * @default true
         * @type  Boolean
         */
        isHidden: true,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 初始化时是否马上显示,为true时马上显示，为false时不马上显示。
         * @default true
         * @type  Boolean
         */
        show: true,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 头部提示。
         * @default 提示
         * @type  String
         */
        title: '提示',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否显示最大化按钮，为true时显示最大化按钮，为false时不显示。
         * @default false
         * @type  Boolean
         */
        showMax: false,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否显示收缩窗口按钮，为true时显示收缩窗口按钮，为false时不显示。
         * @default false
         * @type  Boolean
         */
        showToggle: false,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否显示最小化按钮，为true时显示最小化按钮，为false时不显示。
         * @default false
         * @type  Boolean
         */
        showMin: false,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 是否以动画的形式显示，为true时以动画的形式显示，为false时不以动画的形式显示。
         * @default false
         * @type  Boolean
         */
        slide: $.browser.msie ? false : true,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 在固定的位置显示, 可以设置的值有n, e, s, w, ne, se, sw, nw。
         * @default null
         * @type  Object
         */
        fixedType: null,
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 显示类型,可以设置为slide(固定显示时有效)。
         * @default null
         * @type   Object
         */
        showType: null  ,
        /**
         * 垂直位置  top,middle,bottom
         */
        valign:"middle"
    };
    $.juiceDefaults.DialogString =  /**@lends juiceDialog#*/ {
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提示文本标题。
         * @default 提示
         * @type   String
         */
        titleMessage: '提示',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提示文本确定。
         * @default 确定
         * @type   String
         */
        ok: '确定',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提示文本是。
         * @default 是
         * @type   String
         */
        yes: '是',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提示文本否。
         * @default 否
         * @type   String
         */
        no: '否',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提示文本取消。
         * @default 取消
         * @type   String
         */
        cancel: '取消',
        /**
         *  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提示文本正在等待中,请稍候...
         * @default 正在等待中,请稍候...
         * @type   String
         */
        waittingMessage: '正在等待中,请稍候...'
    };

    $.juiceMethos.Dialog = $.juiceMethos.Dialog || {};


    l.controls.Dialog = function (options)
    {
        l.controls.Dialog.base.constructor.call(this, null, options);
    };
    l.controls.Dialog.juiceExtend(l.core.Win, {
        __getType: function ()
        {
            return 'Dialog';
        },
        __idPrev: function ()
        {
            return 'Dialog';
        },
        _extendMethods: function ()
        {
            return $.juiceMethos.Dialog;
        },
        _render: function ()
        {
            var g = this, p = this.options;
            g.set(p, true);
            var dialog = $('<div class="l-dialog"><table class="l-dialog-table" cellpadding="0" cellspacing="0" border="0"><tbody><tr><td class="l-dialog-tl"></td><td class="l-dialog-tc"><div class="l-dialog-tc-inner"><div class="l-dialog-icon"></div><div class="l-dialog-title"></div><div class="l-dialog-winbtns"><div class="l-dialog-winbtn l-dialog-close"></div></div></div></td><td class="l-dialog-tr"></td></tr><tr><td class="l-dialog-cl"></td><td class="l-dialog-cc"><div class="l-dialog-body"><div class="l-dialog-image"></div> <div class="l-dialog-content"></div><div class="l-dialog-buttons"><div class="l-dialog-buttons-inner"></div></td><td class="l-dialog-cr"></td></tr><tr><td class="l-dialog-bl"></td><td class="l-dialog-bc"></td><td class="l-dialog-br"></td></tr></tbody></table></div>');
//            var dialog = $('<div class="l-dialog"><table class="l-dialog-table" cellpadding="0" cellspacing="0" border="0"><tbody><tr><td class="l-dialog-tc"><div class="l-dialog-tc-inner"><div class="l-dialog-icon"></div><div class="l-dialog-title"></div><div class="l-dialog-winbtns"><div class="l-dialog-winbtn l-dialog-close"></div></div></div></td></tr><tr><td class="l-dialog-cc"><div class="l-dialog-body"><div class="l-dialog-image"></div><div class="l-dialog-content"></div><div class="l-dialog-buttons"><div class="l-dialog-buttons-inner"></div></div></div></td></tr></tbody></table></div>');
            $('body').append(dialog);
            g.dialog = dialog;
            g.element = dialog[0];
            g.dialog.body = $(".l-dialog-body:first", g.dialog);
            g.dialog.header = $(".l-dialog-tc-inner:first", g.dialog);
            g.dialog.winbtns = $(".l-dialog-winbtns:first", g.dialog.header);
            g.dialog.buttons = $(".l-dialog-buttons:first", g.dialog);
            g.dialog.content = $(".l-dialog-content:first", g.dialog);
            g.set(p, false);

            if (p.allowClose == false) $(".l-dialog-close", g.dialog).remove();
            if (p.target || p.url || p.type == "none")
            {
                p.type = null;
                g.dialog.addClass("l-dialog-win");
            }
            if (p.cls) g.dialog.addClass(p.cls);
            if (p.id) g.dialog.attr("id", p.id);
            //设置锁定屏幕、拖动支持 和设置图片
            g.mask();
            if (p.isDrag)
                g._applyDrag();
            if (p.isResize)
                g._applyResize();
            if (p.type)
                g._setImage();
            else
            {
                $(".l-dialog-image", g.dialog).remove();
                g.dialog.content.addClass("l-dialog-content-noimage");
            }
            if (!p.show)
            {
                g.unmask();
                g.dialog.hide();
            }
            //设置主体内容
            if (p.target)
            {
                g.dialog.content.prepend(p.target);
                $(p.target).show();
            }
            else if (p.url)
            {
                if (p.timeParmName)
                {
                    p.url += p.url.indexOf('?') == -1 ? "?" : "&";
                    p.url += p.timeParmName + "=" + new Date().getTime();
                }
                if (p.load)
                {
                    g.dialog.body.load(p.url, function ()
                    {
                        g._saveStatus();
                        g.trigger('loaded');
                    });
                }
                else
                {
                    g.jiframe = $("<iframe frameborder='0' style='overflow: hidden;'></iframe>");
                    g.dialog.content.css("overflow","hidden");
                    var framename = p.name ? p.name : "juicewindow" + new Date().getTime();
                    g.jiframe.attr("name", framename);
                    g.jiframe.attr("id", framename);
                    g.dialog.content.prepend(g.jiframe);
                    g.dialog.content.addClass("l-dialog-content-nopadding");
                    setTimeout(function ()
                    {
                        g.jiframe.attr("src", p.url);
                        g.frame = window.frames[g.jiframe.attr("name")];
                    }, 0);
                }
            }
            if (p.opener) g.dialog.opener = p.opener;
            //设置按钮
            if (p.buttons)
            {
                var widths = 0;
                $(p.buttons).each(function (i, item)
                {
                    var btn = $('<div class="l-dialog-btn"><div class="l-dialog-btn-l"></div><div class="l-dialog-btn-r"></div><div class="l-dialog-btn-inner"></div></div>');
                    $(".l-dialog-btn-inner", btn).html(item.text);
                    $(".l-dialog-buttons-inner", g.dialog.buttons).prepend(btn);
                    item.width && btn.width(item.width);
                    widths += btn.width();
                    if(p.buttonAlign == "center"){
                        if(i==(p.buttons.length - 1)){
                            widths += (p.buttons.length - 1)*5;
                            btn.css({"margin-right":(g.dialog.body.width()-widths)/2});
                        }
                    }

                    item.onclick && btn.click(function () { item.onclick(item, g, i) });
                });
            } else
            {
                g.dialog.buttons.remove();
            }
            $(".l-dialog-buttons-inner", g.dialog.buttons).append("<div class='l-clear'></div>");


            $(".l-dialog-title", g.dialog)
                .bind("selectstart", function () { return false; });
            g.dialog.click(function ()
            {
                l.win.setFront(g);
            });

            //设置事件
            $(".l-dialog-tc .l-dialog-close", g.dialog).click(function ()
            {
                if (p.isHidden)
                    g.hide();
                else
                    g.close();
            });
            if (!p.fixedType)
            {
                //位置初始化
                var left = 0;
                var top = 0;
                var width = p.width || g.dialog.width();
                if (p.slide == true) p.slide = 'fast';
                if (p.left) left = p.left;
                else p.left = left = 0.5 * ($(window).width() - width);
                if (p.top != null) top = p.top;
                else p.top = top = 0.5 * ($(window).height() - g.dialog.height()) + $(window).scrollTop() - 10;
                if (left < 0) p.left = left = 0;
                if (top < 0) p.top = top = 0;
                g.dialog.css({ left: left, top: top });
            }
            if (p.valign) {
                var top = 0;
                if (p.valign == "top") {
                    top = 0;
                } else if (p.valign == "bottom") {
                    top = ($(window).height() - g.dialog.height());
                } else {
                    top = ($(window).height() - g.dialog.height()) / 2;
                }
                g.dialog.css({top:top });

            }
            g.show();
            $('body').bind('keydown.dialog', function (e)
            {
                var key = e.which;
                if (key == 13)
                {
                    g.enter();
                }
                else if (key == 27)
                {
                    g.esc();
                }
            });

            g._updateBtnsWidth();
            g._saveStatus();
            g._onReisze();

        },
        _borderX: 12,
        _borderY: 32,
        doMax: function (slide)
        {
            var g = this, p = this.options;
            var width = $(window).width(), height = $(window).height(), left = 0, top = 0;
            if (l.win.taskbar)
            {
                height -= l.win.taskbar.outerHeight();
                if (l.win.top) top += l.win.taskbar.outerHeight();
            }
            if (slide)
            {
                g.dialog.body.animate({ width: width - g._borderX }, p.slide);
                g.dialog.animate({ left: left, top: top }, p.slide);
                g.dialog.content.animate({ height: height - g._borderY - g.dialog.buttons.outerHeight() }, p.slide, function ()
                {
                    g._onReisze();
                });
            }
            else
            {
                g.set({ width: width, height: height, left: left, top: top });
                g._onReisze();
            }
        },
        //最大化
        max: function ()
        {
            var g = this, p = this.options;
            if (g.winmax)
            {
                g.winmax.addClass("l-dialog-recover");
                g.doMax(p.slide);
                if (g.wintoggle)
                {
                    if (g.wintoggle.hasClass("l-dialog-extend"))
                        g.wintoggle.addClass("l-dialog-toggle-disabled l-dialog-extend-disabled");
                    else
                        g.wintoggle.addClass("l-dialog-toggle-disabled l-dialog-collapse-disabled");
                }
                if (g.resizable) g.resizable.set({ disabled: true });
                if (g.draggable) g.draggable.set({ disabled: true });
                g.maximum = true;

                $(window).bind('resize.dialogmax', function ()
                {
                    g.doMax(false);
                });
            }
        },

        //恢复
        recover: function ()
        {
            var g = this, p = this.options;
            if (g.winmax)
            {
                g.winmax.removeClass("l-dialog-recover");
                if (p.slide)
                {
                    g.dialog.body.animate({ width: g._width - g._borderX }, p.slide);
                    g.dialog.animate({ left: g._left, top: g._top }, p.slide);
                    g.dialog.content.animate({ height: g._height - g._borderY - g.dialog.buttons.outerHeight() }, p.slide, function ()
                    {
                        g._onReisze();
                    });
                }
                else
                {
                    g.set({ width: g._width, height: g._height, left: g._left, top: g._top });
                    g._onReisze();
                }
                if (g.wintoggle)
                {
                    g.wintoggle.removeClass("l-dialog-toggle-disabled l-dialog-extend-disabled l-dialog-collapse-disabled");
                }

                $(window).unbind('resize.dialogmax');
            }
            if (this.resizable) this.resizable.set({ disabled: false });
            if (g.draggable) g.draggable.set({ disabled: false });
            g.maximum = false;
        },

        //最小化
        min: function ()
        {
            var g = this, p = this.options;
            var task = l.win.getTask(this);
            if (p.slide)
            {
                g.dialog.body.animate({ width: 1 }, p.slide);
                task.y = task.offset().top + task.height();
                task.x = task.offset().left + task.width() / 2;
                g.dialog.animate({ left: task.x, top: task.y }, p.slide, function ()
                {
                    g.dialog.hide();
                });
            }
            else
            {
                g.dialog.hide();
            }
            g.unmask();
            g.minimize = true;
            g.actived = false;
        },

        active: function ()
        {
            var g = this, p = this.options;
            if (g.minimize)
            {
                var width = g._width, height = g._height, left = g._left, top = g._top;
                if (g.maximum)
                {
                    width = $(window).width();
                    height = $(window).height();
                    left = top = 0;
                    if (l.win.taskbar)
                    {
                        height -= l.win.taskbar.outerHeight();
                        if (l.win.top) top += l.win.taskbar.outerHeight();
                    }
                }
                if (p.slide)
                {
                    g.dialog.body.animate({ width: width - g._borderX }, p.slide);
                    g.dialog.animate({ left: left, top: top }, p.slide);
                }
                else
                {
                    g.set({ width: width, height: height, left: left, top: top });
                }
            }
            g.actived = true;
            g.minimize = false;
            l.win.setFront(g);
            g.show();
        },

        /**
         *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;展开/收缩dialog。
         * @name  juiceDialog#toggle
         * @function
         * @example   <b>示例:</b> <br>
         *          toggle: function (){
         *           var g = this, p = this.options;
         *           if (!g.wintoggle) return;
         *           if (g.wintoggle.hasClass("l-dialog-extend"))
         *            g.extend();
         *          else
         *            g.collapse();
         *            }
         */
        toggle: function ()
        {

            var g = this, p = this.options;
            if (!g.wintoggle) return;
            if (g.wintoggle.hasClass("l-dialog-extend"))
                g.extend();
            else
                g.collapse();
        },

        //收缩
        /**
         *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收缩dialog。
         * @name  juiceDialog#collapse
         * @function
         * @example   <b>示例:</b> <br>
         *          collapse: function (){
         *           var g = this, p = this.options;
         *           if (!g.wintoggle) return;
         *           if (p.slide)
         *             g.dialog.content.animate({ height: 1 }, p.slide);
         *          else
         *             g.dialog.content.height(1);
         *             if (this.resizable) this.resizable.set({ disabled: true });
         *            }
         */
        collapse: function ()
        {
            var g = this, p = this.options;
            if (!g.wintoggle) return;
            if (p.slide)
                g.dialog.content.animate({ height: 1 }, p.slide);
            else
                g.dialog.content.height(1);
            if (this.resizable) this.resizable.set({ disabled: true });
        },

        /**
         *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;展开dialog。
         * @name  juiceDialog#extend
         * @function
         * @example   <b>示例:</b> <br>
         *  extend: function ()
         *      {
         *          var g = this, p = this.options;
         *          if (!g.wintoggle) return;
         *              var contentHeight = g._height - g._borderY - g.dialog.buttons.outerHeight();
         *          if (p.slide)
         *              g.dialog.content.animate({ height: contentHeight }, p.slide);
         *          else
         *              g.dialog.content.height(contentHeight);
         *          if (this.resizable) this.resizable.set({ disabled: false });
         *         }
         */
        extend: function ()
        {
            var g = this, p = this.options;
            if (!g.wintoggle) return;
            var contentHeight = g._height - g._borderY - g.dialog.buttons.outerHeight();
            if (p.slide)
                g.dialog.content.animate({ height: contentHeight }, p.slide);
            else
                g.dialog.content.height(contentHeight);
            if (this.resizable) this.resizable.set({ disabled: false });
        },
        _updateBtnsWidth: function ()
        {
            var g = this;
            var btnscount = $(">div", g.dialog.winbtns).length;
            g.dialog.winbtns.width(22 * btnscount);
        },
        _setLeft: function (value)
        {
            if (!this.dialog) return;
            if (value != null)
                this.dialog.css({ left: value });
        },
        _setTop: function (value)
        {
            if (!this.dialog) return;
            if (value != null)
                this.dialog.css({ top: value });
        },
        _setWidth: function (value)
        {
            if (!this.dialog) return;
            if (value >= this._borderX)
            {
                this.dialog.body.width(value - this._borderX);
            }
        },
        _setHeight: function (value)
        {
            var g = this, p = this.options;
            if (!this.dialog) return;
            if (value >= this._borderY)
            {
                var height = value - this._borderY - g.dialog.buttons.outerHeight();
                g.dialog.content.height(height);
            }
        },
        _setShowMax: function (value)
        {
            var g = this, p = this.options;
            if (value)
            {
                if (!g.winmax)
                {
                    g.winmax = $('<div class="l-dialog-winbtn l-dialog-max"></div>').appendTo(g.dialog.winbtns)
                        .hover(function ()
                        {
                            if ($(this).hasClass("l-dialog-recover"))
                                $(this).addClass("l-dialog-recover-over");
                            else
                                $(this).addClass("l-dialog-max-over");
                        }, function ()
                        {
                            $(this).removeClass("l-dialog-max-over l-dialog-recover-over");
                        }).click(function ()
                        {
                            if ($(this).hasClass("l-dialog-recover"))
                                g.recover();
                            else
                                g.max();
                        });
                }
            }
            else if (g.winmax)
            {
                g.winmax.remove();
                g.winmax = null;
            }
            g._updateBtnsWidth();
        },
        _setShowMin: function (value)
        {
            var g = this, p = this.options;
            if (value)
            {
                if (!g.winmin)
                {
                    g.winmin = $('<div class="l-dialog-winbtn l-dialog-min"></div>').appendTo(g.dialog.winbtns)
                        .hover(function ()
                        {
                            $(this).addClass("l-dialog-min-over");
                        }, function ()
                        {
                            $(this).removeClass("l-dialog-min-over");
                        }).click(function ()
                        {
                            g.min();
                        });
                    l.win.addTask(g);
                }
            }
            else if (g.winmin)
            {
                g.winmin.remove();
                g.winmin = null;
            }
            g._updateBtnsWidth();
        },
        _setShowToggle: function (value)
        {
            var g = this, p = this.options;
            if (value)
            {
                if (!g.wintoggle)
                {
                    g.wintoggle = $('<div class="l-dialog-winbtn l-dialog-collapse"></div>').appendTo(g.dialog.winbtns)
                        .hover(function ()
                        {
                            if ($(this).hasClass("l-dialog-toggle-disabled")) return;
                            if ($(this).hasClass("l-dialog-extend"))
                                $(this).addClass("l-dialog-extend-over");
                            else
                                $(this).addClass("l-dialog-collapse-over");
                        }, function ()
                        {
                            $(this).removeClass("l-dialog-extend-over l-dialog-collapse-over");
                        }).click(function ()
                        {
                            if ($(this).hasClass("l-dialog-toggle-disabled")) return;
                            if (g.wintoggle.hasClass("l-dialog-extend"))
                            {
                                if (g.trigger('extend') == false) return;
                                g.wintoggle.removeClass("l-dialog-extend");
                                g.extend();
                                g.trigger('extended');
                            }
                            else
                            {
                                if (g.trigger('collapse') == false) return;
                                g.wintoggle.addClass("l-dialog-extend");
                                g.collapse();
                                g.trigger('collapseed')
                            }
                        });
                }
            }
            else if (g.wintoggle)
            {
                g.wintoggle.remove();
                g.wintoggle = null;
            }
        },
        //按下回车
        enter: function ()
        {
            var g = this, p = this.options;
            var isClose;
            if (p.closeWhenEnter != undefined)
            {
                isClose = p.closeWhenEnter;
            }
            else if (p.type == "warn" || p.type == "error" || p.type == "success" || p.type == "question")
            {
                isClose = true;
            }
            if (isClose)
            {
                g.close();
            }
        },
        esc: function ()
        {

        },
        _removeDialog: function ()
        {
            var g = this, p = this.options;
            if (p.showType && p.fixedType)
            {
                g.dialog.animate({ bottom: -1 * p.height }, function ()
                {
                    g.dialog.remove();
                });
            } else
            {
                g.dialog.remove();
            }
        },
        /**
         *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关闭dialog。
         * @name  juiceDialog#close
         * @function
         * @example   <b>示例:</b> <br>
         *         close: function ()
         *         {
         *            var g = this, p = this.options;
         *                 l.win.removeTask(this);
         *                 g.unmask();
         *                 g._removeDialog();
         *                 $('body').unbind('keydown.dialog');
         *         }
         */
        close: function ()
        {
            var g = this, p = this.options;
            l.win.removeTask(this);
            g.unmask();
            g._removeDialog();
            $('body').unbind('keydown.dialog');
        },
        _getVisible: function ()
        {
            return this.dialog.is(":visible");
        },
        _setUrl: function (url)
        {
            var g = this, p = this.options;
            p.url = url;
            if (p.load)
            {
                g.dialog.body.html("").load(p.url, function ()
                {
                    g.trigger('loaded');
                });
            }
            else if (g.jiframe)
            {
                g.jiframe.attr("src", p.url);
            }
        },
        _setContent: function (content)
        {
            this.dialog.content.html(content);
        },
        _setTitle: function (value)
        {
            var g = this; var p = this.options;
            if (value)
            {
                $(".l-dialog-title", g.dialog).html(value);
            }
        },
        _hideDialog: function ()
        {
            var g = this, p = this.options;
            if (p.showType && p.fixedType)
            {
                g.dialog.animate({ bottom: -1 * p.height }, function ()
                {
                    g.dialog.hide();
                });
            } else
            {
                g.dialog.hide();
            }
        },
        hidden: function ()
        {
            var g = this;
            l.win.removeTask(g);
            g.dialog.hide();
            g.unmask();
        },
        show: function ()
        {
            var g = this, p = this.options;
            g.mask();
            if (p.fixedType)
            {
                if (p.showType)
                {
                    g.dialog.css({ bottom: -1 * p.height }).addClass("l-dialog-fixed");
                    g.dialog.show().animate({ bottom: 0 });
                }
                else
                {
                    g.dialog.show().css({ bottom: 0 });
                }
            }
            else
            {
                g.dialog.show();
            }
            //前端显示 
            $.jui.win.setFront.juiceDefer($.jui.win, 100, [g]);
        },
        /**
         * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;封装数据源。
         * @name  juiceDialog#setUrl
         * @param [url] 数据源url
         * @function
         * * @example   <b>示例:</b> <br>
         *        setUrl: function (url)
         *         {
         *                 this._setUrl(url);
         *         }
         */
        setUrl: function (url)
        {
            this._setUrl(url);
        },
        _saveStatus: function ()
        {
            var g = this;
            g._width = g.dialog.body.width();
            g._height = g.dialog.body.height();
            var top = 0;
            var left = 0;
            if (!isNaN(parseInt(g.dialog.css('top'))))
                top = parseInt(g.dialog.css('top'));
            if (!isNaN(parseInt(g.dialog.css('left'))))
                left = parseInt(g.dialog.css('left'));
            g._top = top;
            g._left = left;
        },
        _applyDrag: function ()
        {
            var g = this, p = this.options;
            if ($.fn.juiceDrag)
                g.draggable = g.dialog.juiceDrag({ handler: '.l-dialog-title', animate: false,
                    onStartDrag: function ()
                    {
                        l.win.setFront(g);
                    },
                    onStopDrag: function ()
                    {
                        if (p.target)
                        {
                            var triggers1 = l.find($.jui.controls.DateEditor);
                            var triggers2 = l.find($.jui.controls.ComboBox);
                            //更新所有下拉选择框的位置
                            $($.merge(triggers1, triggers2)).each(function ()
                            {
                                if (this.updateSelectBoxPosition)
                                    this.updateSelectBoxPosition();
                            });
                        }
                        g._saveStatus();
                    }
                });
        },
        _onReisze: function ()
        {
            var g = this, p = this.options;
            if (p.target)
            {
                var manager = $(p.target).juice();
                if (!manager) manager = $(p.target).find(":first").juice();
                if (!manager) return;
                var contentHeight = g.dialog.content.height();
                var contentWidth = g.dialog.content.width();
                manager.trigger('resize', [{ width: contentWidth, height: contentHeight}]);
            }
        },
        _applyResize: function ()
        {
            var g = this, p = this.options;
            if ($.fn.juiceResizable)
            {
                g.resizable = g.dialog.juiceResizable({
                    onStopResize: function (current, e)
                    {
                        var top = 0;
                        var left = 0;
                        if (!isNaN(parseInt(g.dialog.css('top'))))
                            top = parseInt(g.dialog.css('top'));
                        if (!isNaN(parseInt(g.dialog.css('left'))))
                            left = parseInt(g.dialog.css('left'));
                        if (current.diffLeft)
                        {
                            g.set({ left: left + current.diffLeft });
                        }
                        if (current.diffTop)
                        {
                            g.set({ top: top + current.diffTop });
                        }
                        if (current.newWidth)
                        {
                            g.set({ width: current.newWidth });
                            g.dialog.body.css({ width: current.newWidth - g._borderX });
                        }
                        if (current.newHeight)
                        {
                            g.set({ height: current.newHeight });
                        }
                        g._onReisze();
                        g._saveStatus();
                        return false;
                    }, animate: false
                });
            }
        },
        _setImage: function ()
        {
            var g = this, p = this.options;
            if (p.type)
            {
                if (p.type == 'success' || p.type == 'donne' || p.type == 'ok')
                {
                    $(".l-dialog-image", g.dialog).addClass("l-dialog-image-donne").show();
                    g.dialog.content.css({ paddingLeft: 64, paddingBottom: 30 });
                }
                else if (p.type == 'error')
                {
                    $(".l-dialog-image", g.dialog).addClass("l-dialog-image-error").show();
                    g.dialog.content.css({ paddingLeft: 64, paddingBottom: 30 });
                }
                else if (p.type == 'warn')
                {
                    $(".l-dialog-image", g.dialog).addClass("l-dialog-image-warn").show();
                    g.dialog.content.css({ paddingLeft: 64, paddingBottom: 30 });
                }
                else if (p.type == 'question')
                {
                    $(".l-dialog-image", g.dialog).addClass("l-dialog-image-question").show();
                    g.dialog.content.css({ paddingLeft: 64, paddingBottom: 40 });
                }
            }
        }
    });
    l.controls.Dialog.prototype.hide = l.controls.Dialog.prototype.hidden;



    $.juiceDialog.open = function (p)
    {
        return $.juiceDialog(p);
    };
    $.juiceDialog.close = function ()
    {
        var dialogs = l.find(l.controls.Dialog.prototype.__getType());
        for (var i in dialogs)
        {
            var d = dialogs[i];
            d.destroy.juiceDefer(d, 5);
        }
        l.win.unmask();
    };
    $.juiceDialog.show = function (p)
    {
        var dialogs = l.find(l.controls.Dialog.prototype.__getType());
        if (dialogs.length)
        {
            for (var i in dialogs)
            {
                dialogs[i].show();
                return;
            }
        }
        return $.juiceDialog(p);
    };
    $.juiceDialog.hide = function ()
    {
        var dialogs = l.find(l.controls.Dialog.prototype.__getType());
        for (var i in dialogs)
        {
            var d = dialogs[i];
            d.hide();
        }
    };
    $.juiceDialog.tip = function (options)
    {
        options = $.extend({
            showType: 'slide',
            width: 240,
            modal: false,
            height: 150,
            left:window.availWidth*0.8,
            top:window.availHeight*0.8
        }, options || {});

        $.extend(options, {
            fixedType: 'se',
            type: 'none',
            isDrag: false,
            isResize: false,
            showMax: false,
            showToggle: false,
            showMin: false
        });
        return $.juiceDialog.open(options);
    };
    $.juiceDialog.alert = function (content, title, type, callback)
    {
        content = content || "";
        if (typeof (title) == "function")
        {

            callback = title;
            type = null;
        }
        else if (typeof (type) == "function")
        {
            callback = type;
        }
        var btnclick = function (item, Dialog, index)
        {
            Dialog.close();
            if (callback)
                callback(item, Dialog, index);
        };
        p = {
            content: content,
            top:($(window).height()-150)/2,
            buttons: [{ text: $.juiceDefaults.DialogString.ok, onclick: btnclick}]
        };
        if (typeof (title) == "string" && title != "") p.title = title;
        if (typeof (type) == "string" && type != "") p.type = type;
        $.extend(p, {
            showMax: false,
            showToggle: false,
            showMin: false
        });
        return $.juiceDialog(p);
    };

    $.juiceDialog.confirm = function (content, title, callback)
    {
        if (typeof (title) == "function")
        {
            callback = title;
            type = null;
        }
        var btnclick = function (item, Dialog)
        {
            Dialog.close();
            if (callback)
            {
                callback(item.type == 'ok');
            }
        };
        p = {
            type: 'question',
            content: content,
            buttons: [{ text: $.juiceDefaults.DialogString.yes, onclick: btnclick, type: 'ok' }, { text: $.juiceDefaults.DialogString.no, onclick: btnclick, type: 'no'}]
        };
        if (typeof (title) == "string" && title != "") p.title = title;
        $.extend(p, {
            showMax: false,
            showToggle: false,
            showMin: false
        });
        return $.juiceDialog(p);
    };
    $.juiceDialog.warning = function (content, title, callback)
    {
        if (typeof (title) == "function")
        {
            callback = title;
            type = null;
        }
        var btnclick = function (item, Dialog)
        {
            Dialog.close();
            if (callback)
            {
                callback(item.type);
            }
        };
        p = {
            type: 'question',
            content: content,
            buttons: [{ text: $.juiceDefaults.DialogString.yes, onclick: btnclick, type: 'yes' }, { text: $.juiceDefaults.DialogString.no, onclick: btnclick, type: 'no' }, { text: $.juiceDefaults.DialogString.cancel, onclick: btnclick, type: 'cancel'}]
        };
        if (typeof (title) == "string" && title != "") p.title = title;
        $.extend(p, {
            showMax: false,
            showToggle: false,
            showMin: false
        });
        return $.juiceDialog(p);
    };
    $.juiceDialog.waitting = function (title)
    {
        title = title || $.juiceDefaults.Dialog.waittingMessage;
        return $.juiceDialog.open({ cls: 'l-dialog-waittingdialog', type: 'none', content: '<div style="padding:4px">' + title + '</div>', allowClose: false });
    };
    $.juiceDialog.closeWaitting = function ()
    {
        var dialogs = l.find(l.controls.Dialog);
        for (var i in dialogs)
        {
            var d = dialogs[i];
            if (d.dialog.hasClass("l-dialog-waittingdialog"))
                d.close();
        }
    };
    $.juiceDialog.success = function (content, title, onBtnClick)
    {
        return $.juiceDialog.alert(content, title, 'success', onBtnClick);
    };
    $.juiceDialog.error = function (content, title, onBtnClick)
    {
        return $.juiceDialog.alert(content, title, 'error', onBtnClick);
    };
    $.juiceDialog.warn = function (content, title, onBtnClick)
    {
        return $.juiceDialog.alert(content, title, 'warn', onBtnClick);
    };
    $.juiceDialog.question = function (content, title)
    {
        return $.juiceDialog.alert(content, title, 'question');
    };


    $.juiceDialog.prompt = function (title, value, multi, callback)
    {
        var target = $('<input type="text" class="l-dialog-inputtext"/>');
        var height = 60;

        if (typeof (multi) == "function")
        {
            callback = multi;
        }
        if (typeof (value) == "function")
        {
            callback = value;
        }
        else if (typeof (value) == "boolean")
        {
            multi = value;
        }
        if (typeof (multi) == "boolean" && multi)
        {
            target = $('<textarea class="l-dialog-textarea"></textarea>');
            height = 120;
        }
        if (typeof (value) == "string" || typeof (value) == "int")
        {
            target.val(value);
        }
        var btnclick = function (item, Dialog, index)
        {
            Dialog.close();
            if (callback)
            {
                callback(item.type == 'yes', target.val());
            }
        }

        p = {
            title: title,
            target: target,
            width: 320,
            height: height,
            buttons: [{ text: $.juiceDefaults.DialogString.ok, onclick: btnclick, type: 'yes' }, { text: $.juiceDefaults.DialogString.cancel, onclick: btnclick, type: 'cancel'}]
        };
        return $.juiceDialog(p);
    };


})(jQuery);