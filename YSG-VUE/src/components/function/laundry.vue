<template>
  <div>
    <div class="search" style="height: 100%">
      <div class="nav_mark"></div>
      <yd-navbar :title="title" fixed>
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
                        <img src="../../assets/images/artboard.png" width="100%" height="45%">
                    </div>
                </td>
                <td width="5%">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="3" style="height:30px;">&nbsp;</td>
              </tr>
              <tr>
                <td width="5%">&nbsp;</td>
                <td>
                    <div v-if="isZH">
                       机器人将到你的房间收取洗衣，请将要洗的衣物放入洗衣袋，并与洗衣签单一起放入服务机器人。
                    </div>
                    <div v-if="!isZH">
                       The service robot will come to your room to pick up laundry. Please put laundry and the cleaning list into laundry bag and put them into the service robot.
                    </div>
                </td>
                <td width="5%">&nbsp;</td>
              </tr>
          </table>
        </section>
      </section>
    </div>
    <section class="buy_foot">
        <div class="col-5">
          <button type="button" @click="goBack" v-if="isZH" style="border-right:1px solid white;">取消</button>
          <button type="button" @click="goBack" v-if="!isZH" style="border-right:1px solid white;">Cancel</button>
        </div>
        <div class="col-5">
          <button type="button" @click="robotWash" v-if="isZH">提交</button>
          <button type="button" @click="robotWash" v-if="!isZH">Submit</button>
        </div>
      </section>
  </div>
</template>

<style scoped>
  
</style>

<script>
    import { mapGetters } from 'vuex'
    import { mapState } from 'vuex'
    export default {
        data() {
            return {
                isZH:true,
                title:'洗衣服务'
            }
        },
        created:function () {
             //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
                this.title = 'Laundry Service';
            }
            $(function(){
                $(".navbar-center").css('marginLeft',0);
            });
        },
        methods: {
            goBack:function () {
                this.$router.push('/home');
            },
            robotWash: function() {
                let params = {
                    token: localStorage.TOKEN//to: 2206
                };
                //RobotSend
                let dialog = window.YDUI.dialog;
                let alobj = new alertLanguage();
                let obj = alobj.getAlertMsg(localStorage.LANGUAGE);
                let msg = obj.washMsg;
                let sureBnt = obj.sureBnt;
                let title = obj.title;
                this.$store.dispatch('RobotSend', params).then(function (res) {
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


