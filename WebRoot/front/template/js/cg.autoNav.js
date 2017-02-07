;(function($){
	$.fn.autoNav=function(opt){
		opt=$.extend(true,{
			width:null,//���������ܿ�ȣ�Ϊnullʱ�����Զ���ȡ���
			navSelector:null//������ѡ��������Ϊnullʱ��Ĭ��Ϊli
		},opt);
		return $(this).each(function(index){
			var $this=$(this),
			width=$this.width(),
			menus=$this.children(".li1");
			if(opt.width){
				width=opt.width;
			}
			if(opt.navs){
				menus=$this.children(opt.navSelector);
			}
			var menusWidth=0;
			menus.each(function(){
				menusWidth+=$(this).outerWidth();
			});
			var range=parseInt((width-menusWidth)/(menus.length*2));
			menus.css({
				"margin-left":range,
				"margin-right":range			
			});
		});
	};
})(jQuery);