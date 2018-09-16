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
                        <img src="../../assets/images/robotServiceBG.png" width="100%" height="45%">
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
                       您即将跳转到微信页面并通过微信完成商品选购，支付后机器人会将物品送到您的房间。
                    </div>
                    <div v-if="!isZH">
                       You are going to jump to the WeChat page and complete the shopping through WeChat. After payment, the robot will send the items to your room.
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
          <button type="button" @click="lifeShop" v-if="isZH">确定</button>
          <button type="button" @click="lifeShop" v-if="!isZH">Sure</button>
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
                title:'雅诗阁货廊'
            }
        },
        created:function () {
             //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
                this.title = 'Mini-Mart';
            }
            $(function(){
                $(".navbar-center").css('marginLeft',0);
            });
        },
        methods: {
            goBack:function () {
                this.$router.push('/home');
            },
            lifeShop: function() {
                //调用购物柜微信小程序
                let params = {
                    token: localStorage.TOKEN
                };
                this.$store.dispatch("getShoppingBoxDetail", params).then(res => {
                    if (res.code == 0) {
                        launchMiniProgramme(res.data);
                    }else{
                        alert(res.msg);
                    }
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


