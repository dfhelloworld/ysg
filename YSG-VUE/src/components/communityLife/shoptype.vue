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
              <tr>
                <td width="5%" rowspan="2">&nbsp;</td>
                <td colspan="2" height="20px">
                    <div v-if="isZH"><b>洗衣服务</b></div>
                    <div v-if="!isZH"><b>Layndry Service</b></div>
                </td>
              </tr>
              <tr>
                <td colspan="2" height="30px">
                    <div  v-if="isZH"><font color="grey">机器人将到您的房间收取洗衣</font></div>
                    <div  v-if="!isZH"><font color="grey">Robot will come to your room to pick up the laundry</font></div>
                </td>
              </tr>
              <tr>
                <td width="5%">&nbsp;</td>
                <td>
                    <img src="https://storage.easyiservice.com/iservicev2/img/201805/a160af1eb0b8a46348e194ad7ebc3000.jpeg!width_750" width="100%" height="45%" @click="robotWash()">
                </td>
                <td width="5%">&nbsp;</td>
              </tr>
              <tr>
                <td width="5%">&nbsp;</td>
                <td style="border-bottom:1px solid #C0C0C0;">&nbsp;</td>
                <td width="5%" style="border-bottom:1px solid white;">&nbsp;</td>
              </tr>
              <tr>
                  <td colspan="3">
                      <table width="100%" v-for="(firstTag, index) in dataList">
                            <tr>
                                <td width="5%">&nbsp;</td>
                                <td colspan="2" height="30px">
                                    <div v-if="isZH"><b>{{firstTag.title_lang1}}</b></div>
                                    <div v-if="!isZH"><b>{{firstTag.title_lang2}}</b></div>
                                </td>
                            </tr>
                            <tr v-for="(secondTag, index2) in firstTag.children">
                                <td colspan="3">
                                    <ul class="displayShop-ul">
                                            <li>
                                                <div class="img" v-if="firstTag.is_robot == 0">
                                                    <img :src="secondTag.pic" alt=" " @click="robotService(firstTag,secondTag.id)">
                                                </div>
                                                <div class="img" v-if="firstTag.is_robot != 0">
                                                    <img :src="secondTag.pic" alt=" " @click="goDetail(firstTag.id,secondTag.id)">
                                                </div>
                                                <div v-if="isZH">{{secondTag.title_lang1}}</div> 
                                                <div v-if="!isZH">{{secondTag.title_lang2}}</div> 
                                                <div class="divlines"></div>
                                            </li>
                                    </ul>
                                </td>
                            </tr>
                      </table>
                  </td>
              </tr>
          </table>
        </section>
      </section>
    </div>
    <!--购物页面-->
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
        <div class="col-5" v-if="isZH">
          合计 <font color="red">￥ </font><font color="red" id="ftotal">{{ftotal}}</font>
        </div>
        <div class="col-5" v-if="!isZH">
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
        <a href="#" class="icon-chat" @click="goShopCar()">
            <img style="width:100%;height:100%;" src="../../assets/images/shopCard.png" alt="">
        </a> 
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
  .side-bar a {width: 70%;height: 70%;display: inline-block;background-color: white;margin-bottom: 2px;}

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
                    _this.dataList = res.data.list;
                });
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
                
                let _this = this;
                // let params = {
                //     hotelid: localStorage.HOTELID,
                //     shoppingid: this.info.id,
                //     token: localStorage.TOKEN,
                //     count: this.spinner1
                // };
                //this.$store.dispatch("getShoppingOrder", params).then(res => {
                    setTimeout(function(){ 
                        let mymsg = "成功";
                        // if (res.data.code == 0) {
                        //     mymsg = this.language.msg.buy_info;
                        // } else {
                        //     mymsg = res.data.msg;
                        // }
                        $(".buy_foot button").attr("disabled",false);
                        $(".buy_foot button").css({background: "#f0c366"});
                        //弹出购物提示信息
                        let alobj = new alertLanguage();
                        let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                        let title = obj.title;
                        let msg = obj.shopping.msg;
                        let sureBnt = obj.sureBnt;
                        let dialog = window.YDUI.dialog;
                        dialog.confirm(title,mymsg, [
                            {
                                txt: sureBnt,
                                color: false,
                                callback: function () {
                                    global.shopCar.clear();
                                    $("#section1").show();
                                    $("#section3").hide();
                                    $(".side-bar").show();
                                }
                            }
                        ]);
                    }, 3000);
                //});
            },
            toBuy: function(){
                $("#section3").show();
                $("#section2").hide();
            },
            goDetail: function(fId,sId) {
                localStorage.NEWTYPE=fId+','+sId;
                this.$router.push({
                    path: "/newshopping",
                    query: { pageFlag: "home" }
                });
            },
            robotService: function(firstTag,sId) {
                let fId = firstTag.id;
                localStorage.NEWTYPE=fId+','+sId;
                global.firstTag=firstTag;
                this.$router.push({
                    path: "/robotDelivery",
                    query: { pageFlag: "robotDelivery" }
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


