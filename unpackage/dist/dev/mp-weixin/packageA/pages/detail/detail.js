(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["packageA/pages/detail/detail"],{163:function(t,e,n){"use strict";(function(t){n(5);i(n(3));var e=i(n(164));function i(t){return t&&t.__esModule?t:{default:t}}wx.__webpack_require_UNI_MP_PLUGIN__=n,t(e.default)}).call(this,n(1)["createPage"])},164:function(t,e,n){"use strict";n.r(e);var i=n(165),r=n(167);for(var u in r)"default"!==u&&function(t){n.d(e,t,(function(){return r[t]}))}(u);n(169);var c,a=n(11),o=Object(a["default"])(r["default"],i["render"],i["staticRenderFns"],!1,null,"202e5804",null,!1,i["components"],c);o.options.__file="packageA/pages/detail/detail.vue",e["default"]=o.exports},165:function(t,e,n){"use strict";n.r(e);var i=n(166);n.d(e,"render",(function(){return i["render"]})),n.d(e,"staticRenderFns",(function(){return i["staticRenderFns"]})),n.d(e,"recyclableRender",(function(){return i["recyclableRender"]})),n.d(e,"components",(function(){return i["components"]}))},166:function(t,e,n){"use strict";var i;n.r(e),n.d(e,"render",(function(){return r})),n.d(e,"staticRenderFns",(function(){return c})),n.d(e,"recyclableRender",(function(){return u})),n.d(e,"components",(function(){return i}));var r=function(){var t=this,e=t.$createElement;t._self._c},u=!1,c=[];r._withStripped=!0},167:function(t,e,n){"use strict";n.r(e);var i=n(168),r=n.n(i);for(var u in i)"default"!==u&&function(t){n.d(e,t,(function(){return i[t]}))}(u);e["default"]=r.a},168:function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i=r(n(16));function r(t){return t&&t.__esModule?t:{default:t}}var u={data:function(){return{title:"猫猫信息",photo_title:"照片集",pid:"",list:"",tabindex:""}},methods:{backToLast:function(){var e=getCurrentPages();t.navigateBack({url:e[e.length-2].route})},previewImg:function(e){t.previewImage({current:""+e,urls:[e]})},islike:function(){var t=this;i.default.picLike({uid:1,pid:this.list.pid}).then((function(e){"操作成功"==e&&(t.list.pictureList[0].islike=!t.list.pictureList[0].islike,console.log(t.list.pictureList[0].islike))})).catch((function(t){console.log(t)}))}},onLoad:function(e){var n=this;this.tabindex=t.getStorageSync("tabindex"),1==this.tabindex?i.default.catDetail({uid:1,pid:e.data}).then((function(t){n.list=t})).catch((function(t){console.log(t)})):i.default.flowerDetail({uid:1,pid:e.data}).then((function(t){n.list=t})).catch((function(t){console.log(t)}))}};e.default=u}).call(this,n(1)["default"])},169:function(t,e,n){"use strict";n.r(e);var i=n(170),r=n.n(i);for(var u in i)"default"!==u&&function(t){n.d(e,t,(function(){return i[t]}))}(u);e["default"]=r.a},170:function(t,e,n){}},[[163,"common/runtime","common/vendor"]]]);
//# sourceMappingURL=../../../../.sourcemap/mp-weixin/packageA/pages/detail/detail.js.map