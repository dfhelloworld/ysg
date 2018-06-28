<template>
  <div>
    <div class="search" style="height: 100%" id="section1">
      <div class="nav_mark"></div>
      <yd-navbar :title="title" fixed>
        <span class="back" slot="left" @click="goBack()"></span>
      </yd-navbar></br></br></br>
      <div style="width:100%;height:120px;">
            <div style="position:absolute;left:30px;top:104px;font-size:0.4rem;font-family:Avenir-Heavy;color:#ffffff;">ASCOTT HENG SHAN SHANGHAI</div>
            <img src="../../assets/images/oerderBg.png" width="100%" height="100%">
      </div>
      <section class="g-flexview" style="background:white;">
        <section class="g-scrollview">
          <div id="orderDiv" class="m-list list-theme4">
            <ul class="type-buy">
              <li v-for="(order, index) in ordDataList" style="border-bottom:0px;">
                <table border="0" width="100%">
                    <tr>
                        <td>
                            <div style="font-size:24px;font-family:PingFangSC-Semihold;color:#f0c366;">
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
                            <div style="font-size:16px;font-family:PingFangSC-Semihold;color:#4a4a4a;">
                                {{numStr}}:{{order.num}} 
                            </div>
                        </td>
                    </tr>
                     <tr>
                        <td colspan="3" @click="detail(order)">
                            <div style="font-size:16px;font-family:PingFangSC-Regular;color:#f0c366;text-align:center;" v-if="isZH">
                                更多
                            </div>
                            <div style="font-size:16px;font-family:PingFangSC-Regular;color:#f0c366;text-align:center;" v-if="!isZH">
                                MORE
                            </div>
                        </td>
                    </tr>
                </table>
              </li>
            </ul>
          </div>
        </section>
      </section>
    </div>
    <div class="search" style="height: 100%;display:none;" id="section2">
      <div class="nav_mark"></div>
      <yd-navbar :title="title" fixed>
        <span class="back" slot="left" @click="close()"></span>
      </yd-navbar>
      <section class="g-flexview">
        <section class="g-scrollview">
          <div class="m-list list-theme4"><br/><br/>
            <ul class="type-buy" style="padding-top: 0.5rem">
              <li>
                <table border="0" width="100%">
                    <tr>
                        <td width="30%">
                           {{orderNo}}
                        </td>
                        <td width="40%" align="center">
                            {{orderDate}}
                        </td>
                        <td width="30%">
                            &nbsp;
                        </td>
                    </tr>
                </table>
              </li>
              <li v-for="(obj, index) in details">
                <table border="0" width="100%">
                    <tr>
                        <td width="30%">
                           {{obj.title}}
                        </td>
                        <td width="40%" align="center">
                            {{numStr}}:{{obj.num}}
                        </td>
                        <td width="30%">
                            ￥{{obj.price}}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            &nbsp;
                        </td>
                        <td>
                            <font color="red">{{obj.status}}</font>
                        </td>
                    </tr>
                </table>
              </li>
            </ul>
          </div>
        </section>
      </section>
    </div>
  </div>
</template>

<style>
</style>
<script>
    import { mapGetters } from 'vuex'
    import { mapState } from 'vuex'
    export default {
        data() {
            return {
                ordDataList: [],
                details: [],
                isZH:true,
                title:'订单',
                orderNo:'',
                orderDate:'',
                numStr:'数量'
            }
        },
        created:function () {
            //判断显示中/英文
            if(localStorage.LANGUAGE!='zh'){
                this.isZH = false;
                this.title = "Orders";
                this.numStr = "Quantity";
            }
            let _this = this;
            $(function(){
                $(".navbar-center").css('marginLeft',0);
                //获取订单信息
                let params = {
                    token: localStorage.TOKEN,
                    lang:localStorage.LANGUAGE,
                    hotelid:localStorage.HOTELID
                }
                _this.$store.dispatch('viewOrder', params).then(function (res) {
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
                    } else {
                        _this.$dialog.toast({mes: res.msg, timeout: 3000});
                    }
                });
            });
        },
        methods: {
            close:function(){
                $("#section2").hide();
                $("#section1").show();
            },
            goBack:function(){
                this.$router.push('/home');
            },
            detail:function(order){
                $("#section1").hide();
                $("#section2").show();
                this.orderNo = order.id;
                this.orderDate = order.created_at;
                this.details = order.products;
            },
            delOrder:function(id,event){
                let target = event.target;
                if(target==null){
                    target = event.srcElement;
                }
                //$("#orderDiv li:eq("+index+")").remove();
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


