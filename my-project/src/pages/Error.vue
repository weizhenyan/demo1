<template>
  <div class="indexbody"
       :class="backblack.ishow || navselect.flag1 ? 'active':''">
    <!--头部-->
    <div class="error404">
      <div class="errorbox">
        <h6>404</h6>
        <span>抱歉，您访问的页面不存在！</span>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  components: {
  },
  computed: mapGetters([
    'backblack',
    'navselect'
  ])

}
</script>
<style lang="stylus">
.error404 {
  width 100%
  height 575px
  background #ccc
  overflow hidden
  .errorbox {
    width 80%
    background #fff
    text-align Center
    height 150px
    margin 175px auto
    color #ccc
    padding-top 60px
    h6 {
      font-size 60px
      line-height 80px
    }
    span {
      font-size 20px
    }
  }
}
</style>
