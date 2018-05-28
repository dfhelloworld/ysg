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
           <table>
                <tr>
                    <td height="20px">
                        <div v-if="isZH"><font size="3">洗衣服务</font></div>
                        <div v-if="!isZH"><font size="3">Layndry Service</font></div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <img src="https://storage.easyiservice.com/iservicev2/img/201805/a160af1eb0b8a46348e194ad7ebc3000.jpeg!width_750" width="100%" height="45%" @click="robotWash()">
                    </td>
                </tr>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td v-for="(tags, index) in tagsData">
                                    <div v-if="tags.id==tagIds[1]">
                                        <div style="border-bottom:1px solid #DCDCDC;height:30px;float:left;" name="tagsDiv">
                                            <a href="#" @click="changeTab(tags.id)"><span><font size="3">{{tags.title}}</font></span></a>
                                        </div>
                                        <div style="float:left;">&nbsp;&nbsp;&nbsp;</div>
                                    </div>
                                    <div v-if="tags.id!=tagIds[1]">
                                        <div style="color:#afafaf;height:30px;float:left;" name="tagsDiv">
                                            <a href="#" @click="changeTab(tags.id)"><span><font size="3">{{tags.title}}</font></span></a>
                                        </div>
                                        <div style="float:left;">&nbsp;&nbsp;&nbsp;</div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </section>
        <div style="border-bottom:1px solid #ddd;">&nbsp;</div>
        <section class="g-scrollview">
            <ul class="type-buy" style="padding-top: 0.5rem">
              <li v-for="data in dataList" @click="goDetail(data.id)">
                <div class="col-4">
                  <img  :src="data.pic" alt="">
                </div>
                <div class="col-6">
                  <h4>{{data.title}}</h4>
                  <p>{{data.introduct}}</p>
                  <ul class="s-price">
                    <li class="col-5" style="border:0px">RMB {{data.price}}</li>
                    <li class="col-5" style="border:0px"><button type="button">{{language.community.buy}}</button></li>
                  </ul>
                </div>
              </li>
            </ul>
            <p class="no_data" v-show="noData">{{language.common.noMoreData}}</p>
        </section>
      </section>
    </div>
    <!--购物页面-->
    <div class="property" id="section2" style="display: none;">
      <div class="nav_mark"></div>
      <yd-navbar :title="info.title" fixed>
        <span class="back" slot="left" @click="buyClose()"></span>
      </yd-navbar>
      <section class="resolve-box" v-html="content"></section>
      <ul class="pdf-video" :class="{ 'one': isOne, 'two': isTwo}" v-show="btnFlag">
        <li @click="toPDF" v-show="pdfFlag"></li>
        <li @click="toVideo" v-show="videoFlag"></li>
      </ul>
      <section class="buy_foot" style="margin-top: 1rem;">
        <div class="col-5">
          <div><yd-spinner max="75" unit="1" v-model="spinner1"></yd-spinner></div>
        </div>
        <div class="col-5">
          <button type="button" @click="apply">{{language.community.buy}}</button>
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

  .side-bar {width: 20%;position: fixed;bottom: 10%;right: 3%;font-size: 0;line-height: 0;z-index: 100;}
  .side-bar a {width: 70%;height: 70%;display: inline-block;background-color: white;margin-bottom: 2px;}
</style>
<script>
    import { mapGetters } from 'vuex'
    import { mapState } from 'vuex'
    export default {
        data() {
            return {
                dataList: [],
                noData: false,
                pageFlag:'',
                preRoute:this.$route.query.info,
                mySwiper:{},
                info: {},
                num: "",
                spinner1: 0,
                pdfShow: false,
                videoShow: false,
                isOne: false,
                isTwo: false,
                pdfFlag: false,
                videoFlag: false,
                btnFlag: false,
                content: "",
                isZH:true,
                nums:21,
                isShow:true,
                tagIds:[],
                tagsData:[]
            }
        },
        created:function () {
            this.pageFlag = this.$route.query.pageFlag;
            //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
            }
            //根据分类信息
            this.tagIds = localStorage.NEWTYPE.split(',');
            let _this = this;
            $(function(){
                $(".navbar-center").css('marginLeft',0);
                let params = {
                    hotelid: localStorage.HOTELID,
                    status: 0,
                    parentid:_this.tagIds[0]
                }
                _this.$store.dispatch('getFirstTags', params).then(function (res) {
                    _this.tagsData = res.data.data.list;
                });
            });
        },
        methods: {
            buyClose:function(){
                $("#section1").show();
                $("#section2").hide();
            },
            buyCreate: function() {
                $("#section1").hide();
                $("#section2").show();
                this.$store.dispatch("showLoading");
                var ssrBase = "";
                let _this = this;
                if (this.info.detail) {
                    $.get(ssrBase + this.info.detail, function(res) {
                        _this.content = res;
                        _this.$store.dispatch("hideLoading");
                    });
                } else {
                    this.$store.dispatch("hideLoading");
                }
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
            goDetail:function (id) {
                let data = {};
                for(var key in this.dataList){
                    if (this.dataList[key].id == id){
                        data = this.dataList[key];
                        break;
                    }
                }
                this.info = data;
                this.buyCreate();

            },
            goBack:function(){
                this.$router.push('/shopping');
            },
            apply: function() {
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
                            _this.toBuy();
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
            toBuy: function(){
                $(".buy_foot button").attr("disabled",true);
                $(".buy_foot button").css({background: "grey"});
                let _this = this;
                let params = {
                    hotelid: localStorage.HOTELID,
                    shoppingid: this.info.id,
                    token: localStorage.TOKEN,
                    count: this.spinner1
                };
                this.$store.dispatch("getShoppingOrder", params).then(res => {
                    let mymsg = "";
                    if (res.data.code == 0) {
                        mymsg = this.language.msg.buy_info;
                    } else {
                        mymsg = res.data.msg;
                    }
                    $(".buy_foot button").attr("disabled",false);
                    $(".buy_foot button").css({background: "#f0c366"});
                    //弹出购物提示信息
                    let alobj = new alertLanguage();
                    let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                    let title = obj.title;
                    let msg = obj.shopping.msg;
                    let sureBnt = obj.sureBnt;

                    let _this = this;
                    let dialog = window.YDUI.dialog;
                    dialog.confirm(title,mymsg, [
                        {
                            txt: sureBnt,
                            color: false,
                            callback: function () {

                            }
                        }
                    ]);

                });
            },
            toPDF: function() {
                if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                    openFile(this.detail.pdf);
                } else {
                    openPdf(this.detail.pdf);
                }
            },
            toVideo: function() {
                if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
                    openFile(this.detail.video);
                } else {
                    openVideo(this.detail.video);
                }
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
                alert('shopping');
            }
        },
        mounted:function () {
            let _this = this
            //初始化tab标签
            $(function() {
                _this.changeTab(_this.tagIds[1]);
                //
                setTimeout(function(){ 
                    $("div[name='tagsDiv']").click(function(){
                        $("div[name='tagsDiv']").css({"border-bottom": "0px","color":"#afafaf"});
                        $(this).css({"border-bottom": "1px solid #DCDCDC","color":"black"});
                    });
                 }, 1000);
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


