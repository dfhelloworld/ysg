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
                                                    <img :src="secondTag.pic" alt=" " @click="robotService(firstTag.id,secondTag.id)">
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
                isZH:true
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
            goBack:function(){
                if(this.preRoute){
                    this.$router.push('/'+this.preRoute);
                }else{
                    this.$router.push('/home');
                }
            },
            goDetail: function(fId,sId) {
                localStorage.NEWTYPE=fId+','+sId;
                this.$router.push({
                    path: "/newshopping",
                    query: { pageFlag: "home" }
                });
            },
            robotService: function(fId,sId) {
                localStorage.NEWTYPE=fId+','+sId;
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
                alert('shopping');
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


