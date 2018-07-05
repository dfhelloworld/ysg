<template>
  <div>
    <div class="search" style="height: 100%" id="section5">
      <div class="nav_mark"></div>
      <yd-navbar :title="title" fixed>
        <span class="back" slot="left" @click="ordGoBack()"></span>
      </yd-navbar></br></br></br>
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
                               {{order.room_no}}
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
                                {{order.status}} 
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
                            <td>
                                <div style="font-size:24px;font-family:PingFangSC-Semibold;color:#f0c366;">
                                    RMB {{orderTotal}}
                                </div>
                            </td>
                            <td>
                                &nbsp;
                            </td>
                            <td style="text-align: right;">
                                <div style="font-size:18px;color:#f0c366;">
                                    {{roomNo}}&nbsp;&nbsp;&nbsp;
                                </div>
                            </td>
                            <td rowspan="3" width="5%">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div style="font-size:16px;font-family:PingFangSC-Light;color:#4a4a4a;">
                                    {{orderDate}}
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div style="font-size:16px;font-family:PingFangSC-Light;color:#4a4a4a;">
                                    NO. {{orderNo}}
                                </div>
                            </td>
                            <td style="text-align: right;">
                                <div style="font-size:16px;font-family:PingFangSC-Semibold;color:#4a4a4a;">
                                    {{ordStatus}} 
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
                            <td>
                               <div class="dropdown">
                                    <div class="dropdownDiv">
                                        <button class="bnt1" @click="showMenu($event)">处理</button>
                                    </div>
                                    <div class="dropdown-content">
                                        <button class="bnt2" @click="proOrder(obj.orders_products_id,2)">处理中</button>
                                        <button class="bnt2" @click="proOrder(obj.orders_products_id,3)">已完成</button>
                                        <button class="bnt2" @click="proOrder(obj.orders_products_id,4)">已取消</button>
                                    </div>
                               </div>
                            </td>
                            <td width="5%" rowspan="2">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                             <td colspan="2">
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
  </div>
</template>

<style>
.bnt1 {
    background-color: #f0c366;
    border: none;
    color: white;
    padding: 5px 10px 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    border-radius: 4px;
    width: 52px;
}

.bnt2 {
    background-color: white;
    border: 1px solid #4a4a4a;
    color: #4a4a4a;
    padding: 5px 10px 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    border-radius: 4px;
    width: 70px;
    height: 32px;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 70px;
    overflow: auto;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    margin-left: -70px;
    margin-top: -28px;
}

.dropdownDiv {
    position: absolute;
    overflow: auto;
    z-index: 1;
    display:block;
    margin-left: -50px;
    margin-top: -20px;
}
</style>
<script>
    import { PopupPicker, XButton } from 'vux'
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
                orderTotal:0.0,
                orderNum:0,
                numStr:'数量',
                roomNo:'',
                ordStatus:''
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
                let ordParams = {
                    hotelid: localStorage.HOTELID,
                    lang: localStorage.LANGUAGE,
                    page: 1,
                    limit: 10,
                    token: localStorage.TOKEN
                }
                _this.$store.dispatch('getStaffOrderList', ordParams).then(function (res) {
                    if(res.code == 0){
                        _this.ordDataList = res.data.list;
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
            ordClose:function(){
                $(".dropdownDiv").show();
                $(".dropdown-content").hide();
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
                this.roomNo = order.room_no;
                this.ordStatus = order.status;
            },
            showMenu:function(event){
                $(".dropdownDiv").show();
                $(".dropdown-content").hide();
                let target = event.target;
                if(target==null){
                    target = event.srcElement;
                }
                $(target).parent().hide();
                $(target).parent().parent().find(".dropdown-content").show();
            },
            proOrder:function(pid,proState){
                $(".dropdownDiv").show();
                $(".dropdown-content").hide();
                let _this = this;
                let params = {
                    id: pid,
                    token: localStorage.TOKEN,
                    status: proState
                };
                let dialog = window.YDUI.dialog;
                dialog.loading.open('Loading');
                this.$store.dispatch('updateOrderProductById', params).then(function (res) {
                    dialog.loading.close();
                    if(res.code == 0){
                        let rmsg = '处理成功';
                        //判断显示中/英文
                        if(localStorage.LANGUAGE!='zh'){
                            rmsg = "Successful processing";
                        }
                        _this.$dialog.toast({mes: rmsg, timeout: 2000});
                    } else {
                        _this.$dialog.toast({mes: res.msg, timeout: 2000});
                    }
                });
            }
        },
        mounted:function () {
            //一级页面falg
            isHomePage(0)
        },
        components: {
            PopupPicker
        },
        computed: {
            ...mapState({
                language: state => state.language.language
            })
        },
    };
</script>


