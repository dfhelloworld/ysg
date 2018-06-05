<template>
  <div>
    <div class="search" style="height: 100%" id="section1">
      <div class="nav_mark"></div>
      <yd-navbar :title="title" fixed>
        <span class="back" slot="left" @click="goBack()"></span>
      </yd-navbar>
      <section class="g-flexview">
        <section class="g-scrollview">
          <div id="J_ListContent" class="m-list list-theme4"><br/><br/>
            <ul class="type-buy" style="padding-top: 0.5rem">
              <li @click="detail(order)" v-for="(order, index) in dataList">
                <table border="0" width="100%">
                    <tr>
                        <td>
                            {{order.id}}
                        </td>
                        <td>
                            &nbsp;
                        </td>
                        <td style="text-align: right;">
                            <font color="red">{{order.status}}</font>
                        </td>
                    </tr>
                    <tr>
                        <td  width="20%">
                            {{numStr}}:{{order.num}}
                        </td>
                        <td align="center" width="60%">
                            {{order.created_at}}
                        </td>
                        <td valign="right" style="text-align: right;"  width="20%">
                            ￥{{order.price}}
                        </td>
                    </tr>
                </table>
              </li>
            </ul>
            <p class="no_data" v-show="noData">{{language.common.noMoreData}}</p>
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
          <div id="J_ListContent" class="m-list list-theme4"><br/><br/>
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
            <p class="no_data" v-show="noData">{{language.common.noMoreData}}</p>
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
                dataList: [],
                details: [],
                noData: false,
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
                this.title = "Orders";
                this.numStr = "Quantity";
            }
            let _this = this
            $(function(){
                $(".navbar-center").css('marginLeft',0);
                //获取订单信息
                let params = {
                    token: localStorage.TOKEN,
                    lang:localStorage.LANGUAGE,
                    hotelid:localStorage.HOTELID
                }
                this.$store.dispatch('viewOrder', params).then(function (res) {
                    if(res.code == 0){
                        _this.dataList = res.data;
                        for(let i=0;i<_this.dataList.length;i++){
                             // 时间格式转换
                            let date = new Date(Number(_this.dataList[i].created_at));
                            let Y = date.getFullYear() + '-';
                            let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                            let D = date.getDate() + ' ';
                            let h = date.getHours() + ':';
                            let m = date.getMinutes() + ':';
                            let s = date.getSeconds();
                            let dateStr = Y+M+D+h+m+s;
                            _this.dataList[i].created_at = dateStr;
                        }
                    } else {
                        _this.$dialog.toast({mes: res.msg, timeout: 1000});
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


