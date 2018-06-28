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
        <header class="m-navbar">
          <section class="promotiom_index raiders" style="width: 100%">
            <div class="swiper-container">
              <div class="swiper-wrapper ra">
                <div class="swiper-slide" v-for="(item, index) in tagList" @click="changeTab(item.id)" :class="{active:(item.id==tagIds[1])}">
                  <a><span>{{item.title}}</span></a>
                </div>
              </div>
              <div class="swiper-button-prev" v-show="tagList.length>3" style="top:39%"></div>
              <div class="swiper-button-next" v-show="tagList.length>3" style="top:39%"></div>
            </div>
          </section>
        </header>
        <section class="g-scrollview">
          <div id="J_ListContent" class="m-list list-theme4">
            <ul class="type-buy" style="padding-top: 0.5rem">
              <li v-for="data in dataList">
                <div class="col-4">
                  <img  :src="data.pic" alt="" @click="showProduct(data)" style="border:1px solid #f0f0f0;">
                </div>
                <div class="col-6">
                  <div v-if="isZH" style="font-family:Avenir-Roman;font-size:20px;color:#4a4a4a;">{{data.title_lang1}}</div>
                  <div v-if="!isZH" style="font-family:Avenir-Roman;font-size:20px;color:#4a4a4a;">{{data.title_lang2}}</div>
                  <p style="font-family:PingFangSC-Light;font-size:16px;color:#4a4a4a;">{{data.introduct}}</p>
                  <ul class="s-price">
                    <li class="col-6" style="border:0px">
                        <div style="font-family:Avenir-Medium;font-size:20px;color:#f0c366;">RMB {{data.price}}</div>
                    </li>
                    <li class="col-4" style="border:0px">
                        <button type="button"  @click="goDetail(data)" style="border:1px solid #f0c366;width:78px;height:28px;font-size:16px;color:#f0c366;font-family:Helvetica;">{{language.community.buy}}</button>
                    </li>
                  </ul>
                </div>
              </li>
            </ul>
            <p class="no_data" v-show="noData">{{language.common.noMoreData}}</p>
          </div>
        </section>
      </section>
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
      <section class="buy_foot" style="margin-top: 1rem;">
        <div class="col-5" v-if="isZH" style="font-size:16px;">
          合计 <font color="red">￥ </font><font color="red" id="ftotal">{{ftotal}}</font>
        </div>
        <div class="col-5" v-if="!isZH" style="font-size:16px;">
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
      <section class="buy_foot" style="margin-top: 1rem;">
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
    <!--购物车-->
    <div class="side-bar"> 
        <img style="width:60%;height:60%;" src="../../assets/images/shopCard.png" alt="" @click="goShopCar()">
    </div>
    <!--产品详情页面-->
    <!--产品详情页面-->
    <div class="property" id="section4" style="display: none;">
        <div class="nav_mark"></div>
        <yd-navbar :title="language.community.shopping" fixed>
            <span class="back" slot="left" @click="productClose()"></span>
        </yd-navbar>
        <section class="resolve-box" v-html="content"></section>
        </div>
    </div>
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

  .promotiom_index .swiper-slide.active span{
      border-bottom: 2px solid #f0c366;
  }

  .swiper-wrapper {
      border-bottom:1px solid #f0f0f0;
  }

  .side-bar {width: 20%;position: fixed;bottom: 10%;right: 3%;font-size: 0;line-height: 0;z-index: 100;}
</style>
<script>
    import { mapGetters } from 'vuex'
    import { mapState } from 'vuex'
    export default {
        data() {
            return {
                tagList:[],
                dataList: [],
                noData: false,
                pageFlag:'',
                preRoute:this.$route.query.info,
                mySwiper:{},
                tagIds:[],
                isZH:true,
                tagIds:[],
                carList:[],
                ftotal:0,
                content:''
            }
        },
        created:function () {
            this.pageFlag = this.$route.query.pageFlag;
            //根据分类信息
            this.tagIds = localStorage.NEWTYPE.split(',');
            let _this = this;
            $(function(){
                $(".navbar-center").css('marginLeft',0);
            });
        },
        methods: {
            orderClose:function(){
                $("#section2").show();
                $("#section3").hide();
            },
            buyClose:function(){
                $("#section1").show();
                $("#section2").hide();
                $(".side-bar").show();
            },
            buyCreate: function() {
                $("#section1").hide();
                $("#section2").show();
                $(".side-bar").hide();
            },
            productClose: function() {
                $("#section4").hide();
                $("#section1").show();
                $(".side-bar").show();
            },
            changeTab:function (id) {
                let _this = this
                let params = {
                    hotelid: localStorage.HOTELID,
                    lang: localStorage.LANGUAGE,
                    limit:0,
                    tagid:id,
                    status:1
                }

                this.$store.dispatch('getShoppingList', params).then(function (res) {
                    $(".g-scrollview").scrollTop(0);
                    _this.dataList=res.data.data.list;
                    if(_this.dataList.length==0){
                        _this.noData = true;
                    }else{
                        _this.noData = false;
                    }
                });
            },
            goDetail:function (childObj) {
                $(".side-bar").animate({opacity: 0.2},300,function(){
                    $(".side-bar").css({opacity: 1});
                });
                if(global.shopCar===undefined){
                    //初始化购物车
                    global.shopCar = new Map();
                }
                if(global.shopCar.has('p'+global.firstTag.id)){
                    let shopCar = global.shopCar.get('p'+global.firstTag.id);
                    if(shopCar.c.has('c'+childObj.id)){
                        shopCar.c.get('c'+childObj.id).num = shopCar.c.get('c'+childObj.id).num + 1;
                    }else{
                        let cobjs = new Map();
                        let c={c:childObj,num:1};
                        shopCar.c.set('c'+childObj.id,c);
                    }
                }else{
                    let cobjs = new Map();
                    let c={c:childObj,num:1};
                    cobjs.set('c'+childObj.id,c);
                    let obj = {p:global.firstTag,c:cobjs};
                    global.shopCar.set('p'+global.firstTag.id, obj);
                }
                let msg = '加入购物车成功';
                if(localStorage.LANGUAGE!='zh'){
                    msg = 'Add to cart successful';
                }
                this.$dialog.toast({mes: msg, timeout: 1000});
            },
            showProduct:function(obj){
                let data = {};
                for(var key in this.dataList){
                    if (this.dataList[key].id == obj.id){
                        data = this.dataList[key];
                        break;
                    }
                }
                let _this = this;
                if (data.detail) {
                    $.get(data.detail, function(res) {
                        _this.content = res;
                        _this.$store.dispatch("hideLoading");
                    });
                } else {
                    this.$store.dispatch("hideLoading");
                }
                $("#section4").show();
                $("#section1").hide();
                $(".side-bar").hide();
            },
            goBack:function(){
                this.$router.push('/shopping');
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
                                    $(".side-bar").show();
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
            goShopCar: function() {
                this.buyCreate();
                this.caculate();
            },
            caculate: function() {
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
                    pshopCar.c.delete('c'+cid);
                }else{
                    cshopCar.num = cshopCar.num - 1;
                }
                if(pshopCar.c.size==0){
                    global.shopCar.delete('p'+pid);
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
            }
        },
        mounted:function () {
            let _this = this
            let params = {
                hotelid: localStorage.HOTELID,
                status: 0,
                parentid:_this.tagIds[0]
            }
            this.$store.dispatch('getFirstTags', params).then(function (res) {
                _this.tagList = res.data.data.list;
                //初始化tab标签
                $(function() {
                    //初始化tab选择项
                    _this.mySwiper = new Swiper(".raiders .swiper-container", {
                        pagination: ".swiper-pagination",
                        slidesPerView: 3,
                        paginationClickable: true,
                        spaceBetween: 0,
                        initialSlide: 0
                    });
                    //添加标签点击样式
                    $(".ra").on("click", ".swiper-slide", function () {
                        $(this)
                            .addClass("active")
                            .siblings()
                            .removeClass("active");
                    });
                    //tab的click事件触发选择的初始化内容
                    _this.changeTab(_this.tagIds[1]);
                });
            });
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


