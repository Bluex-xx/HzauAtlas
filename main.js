import App from './App';
import MinRequest from './api/MinRequest'
import minRequest from './api/api'
Vue.use(MinRequest);
// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
    ...App,
	minRequest
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif

// 引入并使用uView的JS库，这两行要放在import Vue之后
import uView from "uview-ui";
Vue.use(uView);