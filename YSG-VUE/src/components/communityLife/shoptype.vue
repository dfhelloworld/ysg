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
          <table>
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
                <td width="5%">&nbsp;</td>
                <td colspan="2" height="30px">
                    <div v-if="isZH"><b>机器人服务</b></div>
                    <div v-if="!isZH"><b>Robot Dilivery</b></div>
                </td>
              </tr>
              <tr>
                <td width="5%">&nbsp;</td>
                <td colspan="2">
                    <table width="100%">
                        <tr>
                            <td width="50%" align="left">
                                <div class="img">
                                    <img src="https://storage.easyiservice.com/iservicev2/img/manual/spring_water.jpeg!width_750" @click="robotService(1)">
                                </div>
                            </td>
                            <td width="50%" align="center" v-show="isShow">
                                &nbsp;
                            </td>
                            <td width="50%" align="left" v-show="!isShow">
                                <div class="img">
                                    <img src="https://storage.easyiservice.com/iservicev2/img/manual/souvenir.jpeg!width_750" @click="robotService(0)">
                                </div>
                               
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table width="100%">
                                    <tr>
                                        <td width="43%" align="center">
                                            <div v-if="isZH">客房物品</div>
                                            <div v-if="!isZH">Housekeeping</div>
                                        </td>
                                        <td width="57%" align="center" v-show="isShow">
                                            &nbsp;
                                        </td>
                                        <td width="57%" align="center" v-show="!isShow">
                                            <div v-if="isZH">纪念品</div>
                                            <div v-if="!isZH">Keepsake</div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
              </tr>
              <tr>
                <td width="5%">&nbsp;</td>
                <td style="border-bottom:1px solid #C0C0C0;">&nbsp;</td>
                <td width="5%">&nbsp;</td>
              </tr>
              <tr>
                  <td colspan="3">
                    <table width="100%" v-show="isShow">
                        <tr>
                            <td width="5%" >&nbsp;</td>
                            <td height="30px">
                                <div v-if="isZH"><b>送餐服务</b></div>
                                <div v-if="!isZH"><b>Room Service</b></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <ul class="displayShop-ul" v-show="isShow">
                                        <li>
                                            <div class="img">
                                                <img src="https://storage.easyiservice.com/iservicev2/img/201804/4a39fe8ee10076ed1695bfed50c5925d.jpg!width_750" alt=" " @click="goDetail(11)">
                                            </div> 
                                            <div v-if="isZH">开胃菜</div> 
                                            <div v-if="!isZH">Appetizer</div> 
                                            <div class="divlines"></div>
                                        </li>
                                        <li>
                                            <div class="img">
                                                <img src="https://storage.easyiservice.com/iservicev2/img/201804/17e9a78f54b93d5ab06140b6ac14e39c.jpg!width_750" alt=" " @click="goDetail(16)">
                                            </div> 
                                            <div v-if="isZH">汤</div> 
                                            <div v-if="!isZH">Soup</div> 
                                        </li>
                                        <li>
                                            <div class="img">
                                                <img src="https://storage.easyiservice.com/iservicev2/img/201804/5fec5b70536ccb075017c93d65bf0014.jpg!width_750" alt=" " @click="goDetail(17)">
                                            </div> 
                                            <div v-if="isZH">轻食</div> 
                                            <div v-if="!isZH">Fast Food</div> 
                                            <div class="divlines"></div>
                                        </li>
                                        <li>
                                            <div class="img">
                                                <img src="https://storage.easyiservice.com/iservicev2/img/201804/164ea8e767faea369c8735d3a44e3172.jpg!width_750" alt=" " @click="goDetail(18)">
                                            </div> 
                                            <div v-if="isZH">亚洲精选</div> 
                                            <div v-if="!isZH">Asian Fever</div> 
                                        </li>
                                        <li>
                                            <div class="img">
                                                <img src="https://storage.easyiservice.com/iservicev2/img/201804/3bea4fefee0f8e4a838df4eeed4feb21.jpg!width_750" alt=" " @click="goDetail(19)">
                                            </div> 
                                            <div v-if="isZH">西式风情</div> 
                                            <div v-if="!isZH">Westren Inspired</div> 
                                            <div class="divlines"></div>
                                        </li>
                                        <li>
                                            <div class="img">
                                                <img src="https://storage.easyiservice.com/iservicev2/img/201804/b0159f289b990834c005b5437d0e8676.jpg!width_750" alt=" " @click="goDetail(20)">
                                            </div> 
                                            <div v-if="isZH">饮料</div> 
                                            <div v-if="!isZH">Drinks</div> 
                                        </li>
                                        <li>
                                            <div class="img">
                                                <img src="https://storage.easyiservice.com/iservicev2/img/201804/77293dc99463889531b44f9f948cc3de.jpg!width_750" alt=" " @click="goDetail(21)">
                                            </div> 
                                            <div v-if="isZH">甜品</div> 
                                            <div v-if="!isZH">Dessert</div> 
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
                isShow:true
            }
        },
        created:function () {
            this.pageFlag = this.$route.query.pageFlag;
            //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
            }
            //判断显示广东/深圳
            //console.log("=======HotileId:"+localStorage.HOTELID);//21,7
            if(localStorage.HOTELID!=7){
                this.isShow = false;
            }
            $(function(){
                $(".navbar-center").css('marginLeft',0);
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
            goDetail: function(pid) {
                localStorage.NEWTYPE=pid;
                this.$router.push({
                    path: "/newshopping",
                    query: { pageFlag: "home" }
                });
            },
            robotService: function(v) {
                localStorage.NEWTYPE=v;
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
            }
        },
        mounted:function () {
            let _this = this
            let params = {
                hotelid: localStorage.HOTELID,
                lang: localStorage.LANGUAGE,
                limit:0
            }
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


