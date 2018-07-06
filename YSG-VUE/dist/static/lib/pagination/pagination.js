//Author:Daniel Duan
//Date:06/07/2018
//设置分页对象
window.Pagination = function(target, options) {
    if (!target) {
        alert('分页初始化错误:找不到target!');
        return null;
    }
    this.target = target;//初始化分页目标对象
    this.options = options || {};//初始化参数
    this.lang = this.options.lang || 'zh';//初始化语言
    this.page = this.options.page || 1;//初始化页数
    this.limit = this.options.limit || 10;//初始化每页显示数量
    this.total = this.options.total || 0;//初始化总数量
    this.pages = Math.ceil(this.total / this.limit);//初始化总页数
    this.pageCallBack = this.options.pageCallBack || function(){};//初始化回调函数
    //初始化分页内容
    if(this.pages>1){
        let pageContent = '<a href="#" class="pagerCurrent">'+this.page+'</a>';
      //   for(let i =0;i<this.pages;i++){
      //       if(i==3){
      //           pageContent = pageContent +'<span class="pagerNone">……</span>';
      //       }
      //       if((i+1)==this.page){
      //           pageContent = pageContent +'<a href="#" class="pagerCurrent">'+(i+1)+'</a>';
      //       }else{
      //           pageContent = pageContent +'<a href="#" class="pagerBtnNum">'+(i+1)+'</a>';
      //       }
      //   }
        let prevPageNode = '上一页';
        let nextPageNode = '下一页';
        if(this.lang!='zh'){
            prevPageNode = 'Prev';
            nextPageNode = 'Next';
        }
        let targetHtml=`
                        <div class="pager">
                            <a href="#" class="pagerBtn">`+prevPageNode+`</a>
                            `
                            +pageContent+
                            `
                            <a href="#" class="pagerBtn">`+nextPageNode+`</a>
                        </div>
                    `;
        $(this.target).html(targetHtml);
        //添加点击事件
        let _this = this;
        //上一页
        $(this.target).find("a:eq(0)").click(function(){
            if(_this.page!=1){
                _this.page = _this.page -1;
              //   let prePage = $(_this.target).find(".pagerCurrent").prev(".pagerBtnNum");
              //   $(_this.target).find(".pagerCurrent").addClass("pagerBtnNum");
              //   $(_this.target).find(".pagerCurrent").removeClass("pagerCurrent");
              //   $(prePage).addClass("pagerCurrent");
              //   $(prePage).removeClass("pagerBtnNum");
                _this.pageCallBack(_this.page);
                $(_this.target).find(".pagerCurrent").html(_this.page);
            }
        });
        //下一页
        $(this.target).find("a").last().click(function(){
            if(_this.page!=_this.pages){
                _this.page = _this.page +1;
              //   let nextPage = $(_this.target).find(".pagerCurrent").next(".pagerBtnNum");
              //   $(_this.target).find(".pagerCurrent").addClass("pagerBtnNum");
              //   $(_this.target).find(".pagerCurrent").removeClass("pagerCurrent");
              //   $(nextPage).addClass("pagerCurrent");
              //   $(nextPage).removeClass("pagerBtnNum");
                _this.pageCallBack(_this.page);
                $(_this.target).find(".pagerCurrent").html(_this.page);
            }
        });
        //选择页数
      //   $(this.target).find("a[class!=pagerBtn]").click(function(){
      //       _this.page = Number($(this).html());
      //       $(_this.target).find(".pagerCurrent").addClass("pagerBtnNum");
      //       $(_this.target).find(".pagerCurrent").removeClass("pagerCurrent");
      //       $(this).addClass("pagerCurrent");
      //       $(this).removeClass("pagerBtnNum");
      //       _this.pageCallBack(_this.page);
      //   });
    }
  };
  //设置分页方法
  // Pagination.prototype = {
  //     pageCallBack: function(callback) {
  //         //callback(this.page);
  //         alert(this.page);
  //     }
  // };