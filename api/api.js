import minRequest from './MinRequest';
// const minRequest = new MinRequest();
// minRequest.setConfig((config) => {
//   config.baseURL = 'http://api.somecute.cn:8086/'
//   return config
// })

// export default {
//  //这里统一管理api请求
const api = {
	login(data) {
		return minRequest('cis/login', data, 'POST')
	},
	
	//首页推荐接口
    indexRecommand(data) {
		return minRequest('picture/recommend', data, 'POST');
	},
    
	//首页搜索接口
	indexCatSearch(data) {
		return minRequest('cat/search', data, 'POST');
	},
	indexFlowerSearch(data) {
		return minRequest('flower/search', data, 'POST');
	},
	
	//为照片点赞
	picLike(data) {
		return minRequest('picture/like', data, 'POST')
	},
	
	//猫猫详情
	catDetail(data) {
		return minRequest('cat/catdetail', data, 'POST');
	},
	
	//花花详情
	flowerDetail(data) {
		return minRequest('flower/flowerdetail', data, 'POST');
	},
	
	//猫猫分类
	catColorCategory() {
		return minRequest('cat/findcolor',  '', 'POST')
	},
	catSort(data) {
		return minRequest('cat/classifycolor', data, 'POST');
	},
	
	//花花分类
	//按花期分类
	flowerStateCategory() {
		return minRequest('flower/findflorescence', '', 'POST')
	},
	flowerSortState(data) { 
	    return minRequest('flower/classifyflorescence', data, 'POST')	
	},
	
	//按品种进行分类
	flowerVarietyCategory() {
		return minRequest('flower/findvariety', '', 'POST')
	},
	flowerSortVariety(data)
	{
	    return minRequest('flower/classifyvariety', data, 'POST')		
	},
	
	//返回用户喜欢的猫与花
	userLikePic(data){
		return minRequest('picture/user', data, 'POST')
	}
  }
// }

// export const catSort = () => minRequest('cat/classifycolor', 'POST');
export default api

// 接口使用方法
// Request () {
// 		this.$minApi.indexRecommand({data}).then(res => {
// 			this.res = res
// 			console.log(res)
// 		}).catch(err => {
// 			console.log(err)
// 		})
// 	}