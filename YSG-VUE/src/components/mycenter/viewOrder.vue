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
              <li>
                <table border="0" width="100%">
                    <tr>
                        <td>
                            订单号
                        </td>
                        <td>
                            &nbsp;
                        </td>
                        <td style="text-align: right;">
                            订单状态
                        </td>
                    </tr>
                    <tr>
                        <td>
                            物品个数
                        </td>
                        <td align="center" width="60%">
                            订单时间
                        </td>
                        <td valign="right" style="text-align: right;">
                            订单价格
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
                noData: false,
                isZH:true,
                title:'订单'
            }
        },
        created:function () {
            let _this = this;
            $(function(){
                $(".navbar-center").css('marginLeft',0);
            });
        },
        methods: {
            goBack:function(){
                this.$router.push('/home');
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


