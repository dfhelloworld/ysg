<template>
  <div>
    <div class="search" style="height: 100%" id="section1">
      <div class="nav_mark"></div>
      <yd-navbar :title="language.community.shopping" fixed>
        <span class="close" slot="left" @click="goBack()"></span>
      </yd-navbar>
      <section class="g-flexview">
        <section class="promotiom-box">
          <h1>{{language.raiders.title}}</h1>
        </section>
        <section class="g-scrollview">
          <table id="dataTable">
              <tr name="wash">
                <td width="5%">&nbsp;</td>
                <td>
                    <div>
                        <div v-if="isZH" style="position:absolute;left:30px;top:44px;font-size:0.4rem;font-family:Avenir-Heavy;color:#c96e3c;">洗衣服务</div>
                        <div v-if="!isZH" style="position:absolute;left:30px;top:44px;font-size:0.4rem;font-family:Avenir-Heavy;color:#c96e3c;">Laundry Service</div>
                        <img src="../../assets/images/artboard.png" width="100%" height="45%" @click="robotWash()">
                    </div>
                </td>
                <td width="5%">&nbsp;</td>
              </tr>
              <tr id="nowash">
                  <td colspan="3">
                      <table width="100%" v-for="(firstTag, index) in dataList">
                            <tr>
                                <td width="5%">&nbsp;</td>
                                <td colspan="2" valign="bottom" height="50px">
                                    <div v-if="isZH" style="font-size:0.4rem;font-family:Avenir-Heavy;color:#4a4a4a;">{{firstTag.title_lang1}}</div>
                                    <div v-if="!isZH" style="font-size:0.4rem;font-family:Avenir-Heavy;color:#4a4a4a;">{{firstTag.title_lang2}}</div>
                                </td>
                            </tr>
                            <tr>
                                <td width="5%">&nbsp;</td>
                                <td>
                                    <div class="nav" name="nav" style="background:white;margin:0px;margin-top:5px;height:140px;">
                                        <ul>
                                            <li v-for="(secondTag, index2) in firstTag.children" style="width:148px;">
                                                <table style="width:140px;">
                                                    <tr>
                                                        <td width="140px" height="97px" style="border:1px solid #F0F0F0;">
                                                            <img :src="secondTag.pic" alt=" " @click="goDetail(firstTag,secondTag.id,firstTag.is_robot)" width="140px" height="97px"/>
                                                        </td>
                                                    
                                                    </tr>
                                                    <tr>
                                                        <td width="140px" height="41px" style="border:1px solid #F0F0F0;border-top:0px;">
                                                            <div v-if="isZH" style="font-size:0.28rem;font-family:PingFangSC-Regular;color:#4a4a4a;width:140px;text-align:center;">{{secondTag.title_lang1}}</div> 
                                                            <div v-if="!isZH" style="font-size:0.28rem;font-family:PingFangSC-Regular;color:#4a4a4a;width:140px;text-align:center;">{{secondTag.title_lang2}}</div> 
                                                        </td>
                                                    </tr>
                                                </table>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                                <td width="5%">&nbsp;</td>
                            </tr>
                      </table>
                  </td>
              </tr>
          </table>
        </section>
      </section>
      </br></br>
    </div>
    <!--购物车页面-->
    <div class="property" id="section2" style="display: none;">
      <div class="nav_mark"></div>
      <yd-navbar title="购物车" fixed v-if="isZH">
        <span class="back" slot="left" @click="buyClose()"></span>
        <span style="display:block;width:50%;height:60%;margin-top:10%;margin-right:16%;" slot="right" @click="clearCar()">
           <div style="color:#afafaf;">清空</div>
        </span>
      </yd-navbar>
      <yd-navbar title="Shopping Cart" fixed v-if="!isZH">
        <span class="back" slot="left" @click="buyClose()"></span>
        <span style="display:block;width:50%;height:60%;margin-top:10%;margin-right:16%;" slot="right" @click="clearCar()">
           <div style="color:#afafaf;">Clear</div>
        </span>
      </yd-navbar>
      <section class="g-scrollview"></br></br></br></br>
        <ul class="type-buy" style="margin-top:-20px;" v-for="data in carList">
            <li>
                <div class="col-10" v-if="isZH" style="font-size:16px;">
                    {{data.p.title_lang1}}>
                </div>
                <div class="col-10" v-if="!isZH" style="font-size:16px;">
                    {{data.p.title_lang2}}>
                </div>
            </li>
            <li v-for="p in data.p.c">
                <div class="col-4">
                    <img :src="p.img" alt="">
                </div>
                <div class="col-6">
                    <table width="100%">
                        <tr>
                            <td align="left" colspan="2">
                                <h4 v-if="isZH">{{p.title_lang1}}</h4>
                                <h4 v-if="!isZH">{{p.title_lang2}}</h4>
                            </td>
                            <td align="center">
                                <img style="width:35%;height:20%;" src="../../assets/images/itemDelete.png" alt="" @click="delItem(data.p.id,p.id)">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="height: 0.8rem;">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="20%" align="left">
                                <h4>X{{p.num}}</h4>
                            </td>
                            <td align="right" width="50%">
                                <div>
                                    <span class="m-spinner">
                                        <a href="#" @click="delProduct(data.p.id,p.id)"></a>
                                        <a href="#" @click="addProduct(data.p.id,p.id)"></a>
                                    </span>
                                </div>
                            </td>
                            <td align="center" width="30%">
                                <h4 style="color:red" v-if="isZH">￥ {{p.num*p.price}}</h4>
                                <h4 style="color:red" v-if="!isZH">$ {{p.num*p.price}}</h4>
                            </td>
                        </tr>
                    </table>
                </div>
            </li>
        </ul>
    </section>
      <section class="buy_foot" style="bottom:56px;">
        <div class="col-5" v-if="isZH"  style="font-size:16px;">
          合计 <font color="red">￥ </font><font color="red" id="ftotal">{{ftotal}}</font>
        </div>
        <div class="col-5" v-if="!isZH"  style="font-size:16px;">
          Total <font color="red">$ </font><font color="red" id="ftotal">{{ftotal}}</font>
        </div>
        <div class="col-5">
          <button type="button" @click="apply" v-if="isZH">提交</button>
          <button type="button" @click="apply" v-if="!isZH">Submit</button>
        </div>
      </section>
    </div>
    <!--订单页面-->
    <div class="property" id="section3" style="display: none;">
      <div class="nav_mark"></div>
      <yd-navbar title="订单" fixed v-if="isZH">
        <span class="back" slot="left" @click="orderClose()"></span>
      </yd-navbar>
      <yd-navbar title="Orders" fixed v-if="!isZH">
        <span class="back" slot="left" @click="orderClose()"></span>
      </yd-navbar>
      <section class="g-scrollview"></br></br></br></br>
        <ul class="type-buy" style="margin-top:-20px;" v-for="data in carList">
            <li v-for="p in data.p.c">
                <div class="col-4">
                    <img :src="p.img" alt="">
                </div>
                <div class="col-6">
                    <table width="100%">
                        <tr>
                            <td align="left" colspan="3">
                                <h4 v-if="isZH">{{p.title_lang1}}</h4>
                                <h4 v-if="!isZH">{{p.title_lang2}}</h4>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="height: 0.8rem;">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="20%" align="left" valign="bottom">
                                <h4>X{{p.num}}</h4>
                            </td>
                            <td align="right" width="50%" height="52px">
                                &nbsp;
                            </td>
                            <td align="center" width="30%" valign="bottom">
                                <h4 style="color:red" v-if="isZH">￥ {{p.num*p.price}}</h4>
                                <h4 style="color:red" v-if="!isZH">$ {{p.num*p.price}}</h4>
                            </td>
                        </tr>
                    </table>
                </div>
            </li>
        </ul>
        <ul class="type-buy" style="margin-top:-20px;">
            <li style="border-bottom:0px;">
                <div class="col-10" v-if="isZH" style="font-size:16px;text-align:right;margin-right:7px;">
                    合计 <font color="red">￥ </font><font color="red" id="ftotal">{{ftotal}}</font>
                </div>
                <div class="col-10" v-if="!isZH" style="font-size:16px;text-align:right;margin-right:7px;">
                    Total <font color="red">$ </font><font color="red" id="ftotal">{{ftotal}}</font>
                </div>
            </li>
        </ul>
    </section>
      <section class="buy_foot" style="bottom:56px;">
        <div class="col-5">
          <button type="button" @click="orderClose" v-if="isZH" style="border-right:1px solid white;">取消</button>
          <button type="button" @click="orderClose" v-if="!isZH" style="border-right:1px solid white;">Cancel</button>
        </div>
        <div class="col-5">
          <button type="button" @click="orderApply" v-if="isZH">提交</button>
          <button type="button" @click="orderApply" v-if="!isZH">Submit</button>
        </div>
      </section>
    </div>
    <!--订单功能-->
    <div class="search" style="height: 100%;display:none;" id="section5">
      <div class="nav_mark"></div>
      <yd-navbar :title="title" fixed>
        <span class="back" slot="left" @click="ordGoBack()"></span>
      </yd-navbar></br></br></br>
      <div style="width:100%;height:120px;">
            <div style="position:absolute;left:30px;top:104px;font-size:0.4rem;font-family:Avenir-Heavy;color:#ffffff;">{{hName}}</div>
            <img :src="hUrl" width="100%" height="100%">
      </div>
      <section class="g-flexview" style="background:white;">
        <section class="g-scrollview">
          <div id="orderDiv" class="m-list list-theme4">
            <ul class="type-buy">
              <li v-for="(order, index) in ordDataList" style="border-bottom:0px;">
                <table border="0" width="100%">
                    <tr>
                        <td>
                            <div style="font-size:24px;font-family:PingFangSC-Semibold;color:#f0c366;">
                                RMB {{order.price}}
                            </div>
                        </td>
                        <td>
                            &nbsp;
                        </td>
                        <td style="text-align: right;">
                            <div style="font-size:18px;color:#f0c366;">
                                <a href="#" @click="delOrder(order.id,$event)">X</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div style="font-size:16px;font-family:PingFangSC-Light;color:#4a4a4a;">
                                {{order.created_at}}
                            </div>
                        </td>
                    </tr>
                    <tr style="border-bottom:1px solid #f0f0f0;">
                        <td>
                            <div style="font-size:16px;font-family:PingFangSC-Light;color:#4a4a4a;">
                                NO. {{order.id_show}}
                            </div>
                        </td>
                        <td colspan="2" style="text-align: right;">
                            <div style="font-size:16px;font-family:PingFangSC-Semibold;color:#4a4a4a;">
                                {{numStr}}:{{order.num}} 
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" @click="ordDetail(order)">
                            <div style="font-size:16px;font-family:PingFangSC-Regular;color:#f0c366;text-align:center;" v-if="isZH">
                                更多
                            </div>
                            <div style="font-size:16px;font-family:PingFangSC-Regular;color:#f0c366;text-align:center;" v-if="!isZH">
                                MORE
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" style="background:#f8f8f8;height:20px;">
                            &nbsp;
                        </td>
                    </tr>
                </table>
              </li>
            </ul>
          </div>
        </section>
      </section>
    </div>
    <div class="mask-black-dialog" id="section6" style="display:none;height:100%">   
        <div class="m-confirm" style="height:90%;overflow-y: scroll;">
            <ul class="type-buy" style="width:100%;padding:0px;">
                <li>
                    <table border="0" width="100%">
                        <tr>
                            <td rowspan="3" width="5%">
                                &nbsp;
                            </td>
                            <td colspan="2">
                                <div style="font-size:24px;font-family:PingFangSC-Semibold;color:#f0c366;">
                                    RMB {{orderTotal}}
                                </div>
                            </td>
                            <td rowspan="3" width="5%">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div style="font-size:16px;font-family:PingFangSC-Light;color:#4a4a4a;">
                                    {{orderDate}}
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div style="font-size:16px;font-family:PingFangSC-Light;color:#4a4a4a;">
                                    NO. {{orderNo}}
                                </div>
                            </td>
                            <td style="text-align: right;">
                                <div style="font-size:16px;font-family:PingFangSC-Semibold;color:#4a4a4a;">
                                    {{numStr}}:{{orderNum}} 
                                </div>
                            </td>
                        </tr>

                    </table>
                </li>
                <li v-for="(obj, index) in details">
                    <table border="0" width="100%">
                        <tr>
                            <td width="5%" rowspan="2">
                                &nbsp;
                            </td>
                            <td rowspan="2" width="90px;">
                                <img :src="obj.pic" style="width:79px;height:79px;"/>
                            </td>
                            <td>
                               <div style="font-size:20px;font-family:Avenir-Roman;color:#4a4a4a;">
                                   {{obj.title}}
                               </div>
                            </td>
                            <td width="5%" rowspan="2">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                             <td>
                                 <div style="font-size:16px;font-family:PingFangSC-Light;color:#4a4a4a;">
                                    {{numStr}}:{{obj.num}} RMB {{obj.price}}</br><font color="red">{{obj.status}}</font>
                                </div>
                            </td>
                        </tr>
                    </table>
                </li>
            </ul></br></br>
        </div>
        <div>
            <div style="text-align: center; font-size: 16px; font-family: PingFangSC-Regular; color: rgb(240, 195, 102); position: absolute; left: 7.5%; bottom: 5%; background: #fafafa; width: 85%; height: 30px;" @click="ordClose()" v-if="isZH">
                X 关闭
            </div>
            <div style="text-align: center; font-size: 16px; font-family: PingFangSC-Regular; color: rgb(240, 195, 102); position: absolute; left: 7.5%; bottom: 5%; background: #fafafa; width: 85%; height: 30px;" @click="ordClose()" v-if="!isZH">
                X CLOSE
            </div>
        </div>
    </div>
    <!--Footer start-->
    <footer class="m-tabbar tabbbar-top-line-color tabbar-fixed" style="color: rgb(240, 195, 102); background-color: rgb(255, 255, 255); font-size: 0.24rem; left: 0px;">
        <a href="#" class="tabbar-item" style="color:#f0c366;">
            <span class="tabbar-icon">
                <img src="/static/images/shopping01.png" @click="goShopping()">
                <span class="tabbar-badge"></span> 
            </span> 
            <span class="tabbar-txt" v-if="isZH">体验购物</span>
            <span class="tabbar-txt" v-if="!isZH">Shopping</span>
        </a>
        <a href="#" class="tabbar-item">
            <span class="tabbar-icon">
                <img src="/static/images/shopping04.png" @click="goShopCar()">
                <span class="tabbar-badge"></span> 
            </span> 
            <span class="tabbar-txt" v-if="isZH">购物车</span>
            <span class="tabbar-txt" v-if="!isZH">Shopping Cart</span>
        </a>
        <a href="#" class="tabbar-item">
            <span class="tabbar-icon">
                <img src="/static/images/shopping06.png"  @click="goOrder()">
                <span class="tabbar-badge"></span> 
            </span> 
            <span class="tabbar-txt" v-if="isZH">查看订单</span>
            <span class="tabbar-txt" v-if="!isZH">Check Order</span>
        </a>
    </footer>
    <!--Footer end-->
  </div>
</template>

<style>
  .buy_foot {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    background: none;
    height: 1rem;
    line-height: 1rem;
    text-align: center;
    background: #fff;
  }
  .buy_foot button {
    width: 100%;
    height: 1rem;
    color: #fff;
    background: #f0c366;
    border: none;
    font-size: 0.3rem;
  }
  .buy_detail img {
    width: 100%;
    height: 5rem;
  }
  .buy_txt {
    padding: 0.3rem;
  }
  .buy_txt h4 {
    font-size: 0.28rem;
    color: #333;
  }
  .buy_txt p {
    font-size: 0.24rem;
    color: #666;
  }
  .m-spinner {
    margin-top: 0.2rem;
  }

  .displayShop-ul {
    display: flex;
    flex-wrap: wrap;
  }
  .displayShop-ul li {
    width: 50%;
    height: 100%;
    text-align: center;
    padding-top: 10px;
  }
  .img {
    width: 3rem;
    display: inline-block;
    height: 3rem;
  }
  .img img {
    max-width: 100%;
  }

  .divlines{
    background:#C0C0C0;width:180%;display:block;height:1px;margin-left:10%;
  }

  .side-bar {width: 20%;position: fixed;bottom: 10%;right: 3%;font-size: 0;line-height: 0;z-index: 100;}
  /*滚动样式*/
  .nav{width:100%;overflow:hidden;margin:0 auto;height:35px;position:relative; line-height:35px;background-color:#000;}
  .nav ul{position:absolute;left:0;top:0;width:640px;z-index:1;list-style:none;}
  .nav ul li{width:80px; float:left; overflow:hidden;list-style:none;}
  .nav a{color:#fff;width:100%; display:block; text-decoration:none; text-align:center;}
</style>
<script>
window.Swipe = function(b, a) {
            if (!b) {
                return null;
            }
            this.options = a || {};
            this.index = this.options.startSlide || 0;
            this.speed = this.options.speed || 300;
            this.lwidth = this.options.width || 80;
            this.delay = this.options.auto || 0;
            this.container = b;
            this.element = this.container.children[0];
            this.lmove = $(this.container.children[0]).find("li").length * this.lwidth - this.lwidth - this.lwidth;
            
            this.setup();
        
            if (this.delay != 0) {
                this.begin();
            }
            if (this.element.addEventListener) {
                this.element.addEventListener("touchstart", this, false);
                this.element.addEventListener("touchmove", this, false);
                this.element.addEventListener("touchend", this, false);
                this.element.addEventListener("transitionend", this, false);
            }
        };
        Swipe.prototype = {
            setup: function() {
                this.slides = this.element.children;
                this.width = Math.ceil(("getBoundingClientRect" in this.container) ? this.container.getBoundingClientRect().width: this.container.offsetWidth);
                if (!this.width||this.slides.length < 1) {
                    return null
                }
                this.element.style.width = Math.ceil(this.slides.length * this.lwidth) + "px";
                var a = this.slides.length;
                while (a--) {
                    var b = this.slides[a];
                    b.style.width = this.lwidth + "px";
                }
                this.slide(this.index, 0);
            },
            slide: function(a, c) {
                var b = this.element.style;
                if (c == undefined) {
                    c = this.speed
                }
                b.webkitTransitionDuration = b.MozTransitionDuration = b.msTransitionDuration = b.OTransitionDuration = b.transitionDuration = c + "ms";
                this.index = a;
            },
            next: function(a) {
                this.delay = a || 0;
                clearTimeout(this.interval);
                if (this.index < this.length - 1) {
                    this.slide(this.index + 1, this.speed);
                } else {
                    this.slide(0, this.speed);
                }
            },
            begin: function() {
                var a = this;
                this.interval = (this.delay) ? setTimeout(function() {
                    a.next(a.delay)
                },
                this.delay) : 0
            },
            stop: function() {
                this.delay = 0;
                clearTimeout(this.interval)
            },
            handleEvent: function(a) {
                switch (a.type) {
                case "touchstart":
                    this.onTouchStart(a);
                    break;
                case "touchmove":
                    this.onTouchMove(a);
                    break;
                case "touchend":
                    this.onTouchEnd(a);
                    break;
                case "transitionend":
                    this.transitionEnd(a);
                    break;
                }
            },
            transitionEnd: function(a) {
                if (this.delay) {
                    this.begin()
                }
                
            },
            onTouchStart: function(a) {
                this.start = {
                    pageX: a.touches[0].pageX,
                    pageY: a.touches[0].pageY,
                    time: Number(new Date())
                    
                };
                this.isScrolling = undefined;
                this.deltaX = 0;
                this.element.style.MozTransitionDuration = this.element.style.webkitTransitionDuration = 0;
                a.stopPropagation()
            },
            onTouchMove: function(a) {
                if (a.touches.length > 1 || a.scale && a.scale !== 1) {
                    return
                }
                this.deltaX = a.touches[0].pageX - this.start.pageX;
                if (typeof this.isScrolling == "undefined") {
                    this.isScrolling = !!(this.isScrolling || Math.abs(this.deltaX) < Math.abs(a.touches[0].pageY - this.start.pageY))
                }
                if (!this.isScrolling) {
                    a.preventDefault();
                    clearTimeout(this.interval);
                    this.deltaX = this.deltaX / ((!this.index && this.deltaX > 0 || this.index == this.length - 1 && this.deltaX < 0) ? (Math.abs(this.deltaX) / this.width + 1) : 1);
                    let mvx =  (this.deltaX - this.index * this.width);
                    if(((this.deltaX - this.index * this.width)+this.lmove)<this.lwidth){
                        mvx = - this.lmove;
                    }
                    if(mvx >0 ){
                        mvx = 0;
                    }
                    this.element.style.MozTransform = this.element.style.webkitTransform = "translate3d(" + mvx + "px,0,0)";
                    a.stopPropagation();
                }
            },
            onTouchEnd: function(c) {
                var b = Number(new Date()) - this.start.time < 250 && Math.abs(this.deltaX) > 20 || Math.abs(this.deltaX) > this.width / 2,
                a = !this.index && this.deltaX > 0 || this.index == this.length - 1 && this.deltaX < 0;
                if (!this.isScrolling) {
                    this.slide(this.index + (b && !a ? (this.deltaX < 0 ? 1: -1) : 0), this.speed)
                }
                c.stopPropagation()
            }
        };

    import { mapGetters } from 'vuex'
    import { mapState } from 'vuex'
    export default {
        data() {
            return {
                dataList:[],
                isZH:true,
                carList:[],
                ftotal:0,
                ordDataList: [],
                details: [],
                isZH:true,
                title:'订单',
                orderNo:'',
                orderDate:'',
                orderTotal:0.0,
                orderNum:0,
                numStr:'数量',
                shopShow: false,
                hUrl:'',
                hName:''
            }
        },
        created:function () {
            //初始化酒店图片和名称
            this.hUrl = localStorage.HomeInfo.split(';')[0];
            this.hName = localStorage.HomeInfo.split(';')[1];
            //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
                this.title = "Orders";
                this.numStr = "Quantity";
            }
            let _this = this;
            $(function(){
                $(".navbar-center").css('marginLeft',0);
                //获取分类
                let params = {
                    hotelid: localStorage.HOTELID,
                    status: 0,
                    parentid:0,
                    withchild:1
                }
                _this.$store.dispatch('getFirstTags', params).then(function (res) {
                    _this.dataList = res.data.data.list;
                    setTimeout(function(){
                        let navs = document.getElementsByName('nav');
                        for(let i = 0; i<navs.length; i++){
                            if($(navs[i]).find("li").length>2){
                                new Swipe(navs[i],{speed:500,auto:0,width:148,col:4,});
                            }
                        }
                    },300);
                });
                //是否显示机器人洗衣服务
                if(localStorage.WASHING_MACHINE != 1){
                    $("#dataTable tr[name='wash']").hide();
                    let html = `
                    <tr>
                        <td width="5%">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td width="5%">&nbsp;</td>
                    </tr>
                    `;
                    $("#dataTable").prepend(html);
                }
                //获取订单信息
                let ordParams = {
                    token: localStorage.TOKEN,
                    lang:localStorage.LANGUAGE,
                    hotelid:localStorage.HOTELID
                }
                _this.$store.dispatch('viewOrder', ordParams).then(function (res) {
                    if(res.code == 0){
                        _this.ordDataList = res.data;
                        for(let i=0;i<_this.ordDataList.length;i++){
                            // 时间格式转换
                            let date = new Date(Number(_this.ordDataList[i].created_at) * 1000);
                            let Y = date.getFullYear() + '-';
                            let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                            let D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate()) + ' ';
                            let h = (date.getHours() < 10 ? '0'+(date.getHours()) : date.getHours()) + ':';
                            let m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
                            let s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
                            let dateStr = Y+M+D+h+m+s;
                            _this.ordDataList[i].created_at = dateStr;
                        }
                    }
                });
                //判断购物车显示图片
                _this.shopCarImg();
            });
        },
        methods: {
            goOrder:function(){
                $("footer a").css("color", "#979797");
                $("footer a:eq(2)").css("color", "#f0c366");
                $("footer img:eq(0)").attr("src","/static/images/shopping02.png");
                //判断购物车显示图片
                this.shopShow = false;
                this.shopCarImg();
                $("footer img:eq(2)").attr("src","/static/images/shopping05.png");
                $("#section1").hide();
                $("#section2").hide();
                $("#section3").hide();
                $("#section5").show();
                $("#section6").hide();
            },
            goShopping:function(){
                $("footer a").css("color", "#979797");
                $("footer a:eq(0)").css("color", "#f0c366");
                $("footer img:eq(0)").attr("src","/static/images/shopping01.png");
                //判断购物车显示图片
                this.shopShow = false;
                this.shopCarImg();
                $("footer img:eq(2)").attr("src","/static/images/shopping06.png");
                $("#section1").show();
                $("#section2").hide();
                $("#section3").hide();
                $("#section5").hide();
                $("#section6").hide();
            },
            orderClose:function(){
                $("#section2").show();
                $("#section3").hide();
            },
            buyClose:function(){
                $("#section1").show();
                $("#section2").hide();
            },
            goBack:function(){
                if(this.preRoute){
                    this.$router.push('/'+this.preRoute);
                }else{
                    this.$router.push('/home');
                }
            },
            apply: function() {
                let dialog = window.YDUI.dialog;
                let alobj = new alertLanguage();
                let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                let title = obj.title;
                let sureBnt = obj.sureBnt;
                let msg = '请选择商品';
                if(localStorage.LANGUAGE!='zh'){
                    msg = 'Please select product';
                }
                let _this = this;
                if(global.shopCar.size==0){
                    dialog.confirm(title,msg, [
                        {
                            txt: sureBnt,
                            color: false,
                            callback: function () {
                                _this.caculate();
                            }
                        }
                    ]);
                }else{
                    $("#section3").show();
                    $("#section2").hide();
                }
            },
            orderApply: function() {
                let alobj = new alertLanguage();
                let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                let title = obj.title;
                let msg = obj.shopping.msg;
                let sureBnt = obj.sureBnt;
                let cancelBnt = obj.cancelBnt;

                let _this = this;
                let dialog = window.YDUI.dialog;
                dialog.confirm(title,msg, [
                    {
                        txt: sureBnt,
                        color: false,
                        callback: function () {
                            _this.doOrder();
                        }
                    },
                    {
                        txt: cancelBnt,
                        color: false,
                        callback: function () {

                        }
                    }
                ]);
            },
            doOrder: function(){
                $(".buy_foot button").attr("disabled",true);
                $(".buy_foot button").css({background: "grey"});
                //提交订单
                let arr = "[";
                for(let i = 0;i < this.carList.length;i++){
                    for(let j = 0;j < this.carList[i].p.c.length;j++){
                        arr = arr + '{"id":' +this.carList[i].p.c[j].id +',"num":'+this.carList[i].p.c[j].num +'},';
                    }
                }
                arr = arr.substring(0,arr.length-1) +"]";
                let _this = this;
                let params = {
                    hotelid: localStorage.HOTELID,
                    userid: localStorage.userId,
                    token: localStorage.TOKEN,
                    products: arr
                };
                this.$store.dispatch("addShoppingCart", params).then(res => {
                    let mymsg = "成功";
                    //判断显示中/英文
                    if(localStorage.LANGUAGE!='zh'){
                        mymsg = "success";
                    }
                    if (res.data.code != 0) {
                        mymsg = res.data.msg;
                    }
                    $(".buy_foot button").attr("disabled",false);
                    $(".buy_foot button").css({background: "#f0c366"});
                    //弹出购物提示信息
                    let alobj = new alertLanguage();
                    let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                    let title = obj.title;
                    let sureBnt = obj.sureBnt;
                    let dialog = window.YDUI.dialog;
                    dialog.confirm(title,mymsg, [
                        {
                            txt: sureBnt,
                            color: false,
                            callback: function () {
                                if (res.data.code == 0) {
                                    global.shopCar.clear();
                                    $("#section1").show();
                                    $("#section3").hide();
                                }
                            }
                        }
                    ]);
                });
            },
            toBuy: function(){
                $("#section3").show();
                $("#section2").hide();
            },
            goDetail: function(firstTag,sId,isRobot) {
                let fId = firstTag.id;
                localStorage.NEWTYPE=fId+','+sId;
                global.firstTag=firstTag;
                let p = "/newshopping";
                if(isRobot==0){
                    p = "/robotDelivery";
                }
                this.$router.push({
                    path: p,
                    query: {pageFlag:"home"}
                });
            },
            robotWash: function() {
                 let dialog = window.YDUI.dialog;
                 let alobj = new alertLanguage();
                 let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                 let title = obj.title;
                 let sureBnt = obj.sureBnt;
                 let cancelBnt = obj.cancelBnt;
                 let msg = obj.washMsg;
                 let _this = this;
                 dialog.confirm(title,msg, [
                        {
                            txt: sureBnt,
                            color: false,
                            callback: function () {
                                let params = {
                                    token: localStorage.TOKEN//to: 2206
                                };
                                //RobotSend
                                _this.$store.dispatch('RobotSend', params).then(function (res) {
                                    msg = obj.successMsg;
                                    if(res.data.code!=0){
                                        msg = obj.sytemBusy;
                                    }
                                    dialog.confirm(title,msg, [
                                        {
                                            txt: sureBnt,
                                            color: false,
                                            callback: function () {
                                                
                                            }
                                        }
                                    ]);
                                });
                            }
                        },
                        {
                            txt: cancelBnt,
                            color: false,
                            callback: function () {

                            }
                        }
                 ]);
            },
            goShopCar: function() {
                $("footer a").css("color", "#979797");
                $("footer a:eq(1)").css("color", "#f0c366");
                $("footer img:eq(0)").attr("src","/static/images/shopping02.png");
                //判断购物车显示图片
                this.shopShow = true;
                $("footer img:eq(2)").attr("src","/static/images/shopping06.png");
                $("#section1").hide();
                $("#section2").show();
                $("#section3").hide();
                $("#section5").hide();
                $("#section6").hide();
                this.caculate();
            },
            caculate: function() {
                if(global.shopCar===undefined){
                    //初始化购物车
                    global.shopCar = new Map();
                }
                let total = 0;
                let arr = [];
                global.shopCar.forEach(function(value, key, map){
                    let pobj = {p:{id:value.p.id,title_lang1:value.p.title_lang1,title_lang2:value.p.title_lang2,c:[]}};
                    value.c.forEach(function(v2, k2, m2){
                        let cobj = {id:v2.c.id,title_lang1:v2.c.title_lang1,title_lang2:v2.c.title_lang2,img:v2.c.pic,num:v2.num,price:v2.c.price};
                        pobj.p.c.push(cobj);
                        total = total + cobj.num * cobj.price;
                    });
                    arr.push(pobj);
                });
                this.carList = arr;
                this.ftotal = total;
                //判断购物车显示图片
                this.shopCarImg();
            },
            clearCar: function() {
                global.shopCar.clear();
                let dialog = window.YDUI.dialog;
                let alobj = new alertLanguage();
                let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                let title = obj.title;
                let sureBnt = obj.sureBnt;
                let msg = '购物车已清空';
                if(localStorage.LANGUAGE!='zh'){
                    msg = 'Shopping cart is empty';
                }
                let _this = this;
                dialog.confirm(title,msg, [
                    {
                        txt: sureBnt,
                        color: false,
                        callback: function () {
                            _this.caculate();
                        }
                    }
                ]);
            },
            addProduct: function(pid,cid) {
                let pshopCar = global.shopCar.get('p'+pid);
                let cshopCar = pshopCar.c.get('c'+cid);
                cshopCar.num = cshopCar.num + 1;
                this.caculate();
            },
            delProduct: function(pid,cid) {
                let pshopCar = global.shopCar.get('p'+pid);
                let cshopCar = pshopCar.c.get('c'+cid);
                if(cshopCar.num==1){
                    return;
                }else{
                    cshopCar.num = cshopCar.num - 1;
                }
                this.caculate();
            },
            delItem: function(pid,cid) {
                let pshopCar = global.shopCar.get('p'+pid);
                let cshopCar = pshopCar.c.get('c'+cid);
                pshopCar.c.delete('c'+cid);
                if(pshopCar.c.size==0){
                    global.shopCar.delete('p'+pid);
                }
                this.caculate();
            },
            ordClose:function(){
                $("#section6").hide();
                $("#section5").show();
            },
            ordGoBack:function(){
                this.$router.push('/home');
            },
            ordDetail:function(order){
                $("#section5").hide();
                $("#section6").show();
                this.orderNo = order.id_show;
                this.orderDate = order.created_at;
                this.orderTotal = order.price;
                this.orderNum = order.num;
                this.details = order.products;
            },
            delOrder:function(id,event){
                let target = event.target;
                if(target==null){
                    target = event.srcElement;
                }
                let _this = this;
                let alobj = new alertLanguage();
                let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                let title = obj.title;
                let msg1 = '是否删除?';
                let msg2 = '删除成功!';
                //判断显示中/英文
                if(localStorage.LANGUAGE!='zh'){
                    msg1 = 'Are you sure to delete the order?';
                    msg2 = 'Successfully deleted!';
                }
                let sureBnt = obj.sureBnt;
                let cancelBnt = obj.cancelBnt;
                let dialog = window.YDUI.dialog;
                dialog.confirm(title,msg1, [
                    {
                        txt: sureBnt,
                        color: false,
                        callback: function () {
                            let params = {
                                token: localStorage.TOKEN,
                                orderid:id
                            }
                            _this.$store.dispatch('deleteOrder', params).then(function (res) {
                                if(res.code==0){
                                    _this.$dialog.toast({mes: msg2, timeout: 2000});
                                    $(target).parent().parent().parent().parent().parent().remove();
                                }else{
                                    _this.$dialog.toast({mes: res.msg, timeout: 2000});
                                }
                            });
                        }
                    },
                    {
                        txt: cancelBnt,
                        color: false,
                        callback: function () {

                        }
                    }
                ]);
            },
            shopCarImg:function(){
                if(this.shopShow){
                    if((global.shopCar===undefined)||(global.shopCar.size==0)){
                        $("footer img:eq(1)").attr("src","/static/images/shopping03.png");
                    }else{
                        $("footer img:eq(1)").attr("src","/static/images/shopping07.png");
                    }
                }else{
                    if((global.shopCar===undefined)||(global.shopCar.size==0)){
                        $("footer img:eq(1)").attr("src","/static/images/shopping04.png");
                    }else{
                        $("footer img:eq(1)").attr("src","/static/images/shopping08.png");
                    }
                }
            }
        },
        mounted:function () {
            //一级页面falg
            isHomePage(0)
        },
        components: {
        },
        computed: {
            ...mapState({
                language: state => state.language.language
            })
        },
    };
</script>


