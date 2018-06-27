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
                                    <ul class="displayShop-ul">
                                        <li v-for="(secondTag, index2) in firstTag.children">
                                            <div style="border:1px solid #F0F0F0;width:152px;">
                                                <div class="img">
                                                    <img :src="secondTag.pic" alt=" " @click="goDetail(firstTag,secondTag.id,firstTag.is_robot)" width="100%" height="100%"/>
                                                </div>
                                                <div v-if="isZH" style="font-size:0.28rem;font-family:PingFangSC-Regular;color:#4a4a4a;">{{secondTag.title_lang1}}</div> 
                                                <div v-if="!isZH" style="font-size:0.28rem;font-family:PingFangSC-Regular;color:#4a4a4a;">{{secondTag.title_lang2}}</div> 
                                            </div>
                                        </li>
                                    </ul>
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
      <section class="buy_foot" style="margin-top: 1rem;">
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
    <!--Footer start-->
    <footer class="m-tabbar tabbbar-top-line-color tabbar-fixed" style="color: rgb(240, 195, 102); background-color: rgb(255, 255, 255); font-size: 0.24rem; left: 0px;">
        <a href="#" class="tabbar-item" style="color:#f0c366;">
            <span class="tabbar-icon">
                <img src="/static/images/shopping01.png" @click="goShopping()">
                <span class="tabbar-badge"></span> 
            </span> 
            <span class="tabbar-txt">体验购物</span>
        </a>
        <a href="#" class="tabbar-item">
            <span class="tabbar-icon">
                <img src="/static/images/shopping04.png" @click="goShopCar()">
                <span class="tabbar-badge"></span> 
            </span> 
            <span class="tabbar-txt">购物车</span>
        </a>
        <a href="#" class="tabbar-item">
            <span class="tabbar-icon">
                <img src="/static/images/shopping06.png"  @click="goOrder()">
                <span class="tabbar-badge"></span> 
            </span> 
            <span class="tabbar-txt">查看订单</span>
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

</style>
<script>
    import { mapGetters } from 'vuex'
    import { mapState } from 'vuex'
    export default {
        data() {
            return {
                dataList:[],
                isZH:true,
                carList:[],
                ftotal:0
            }
        },
        created:function () {
            //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
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
            });
        },
        methods: {
            goOrder:function(){
                $("footer a").css("color", "#979797");
                $("footer a:eq(2)").css("color", "#f0c366");
                $("footer img:eq(0)").attr("src","/static/images/shopping02.png");
                $("footer img:eq(1)").attr("src","/static/images/shopping04.png");
                $("footer img:eq(2)").attr("src","/static/images/shopping05.png");
            },
            goShopping:function(){
                $("footer a").css("color", "#979797");
                $("footer a:eq(0)").css("color", "#f0c366");
                $("footer img:eq(0)").attr("src","/static/images/shopping01.png");
                $("footer img:eq(1)").attr("src","/static/images/shopping04.png");
                $("footer img:eq(2)").attr("src","/static/images/shopping06.png");
                $("#section1").show();
                $("#section2").hide();
                $("#section3").hide();
            },
            orderClose:function(){
                $("#section2").show();
                $("#section3").hide();
            },
            buyClose:function(){
                $("#section1").show();
                $("#section2").hide();
            },
            buyCreate: function() {
                $("#section1").hide();
                $("#section2").show();
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
                $("footer img:eq(1)").attr("src","/static/images/shopping03.png");
                $("footer img:eq(2)").attr("src","/static/images/shopping06.png");
                this.buyCreate();
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


