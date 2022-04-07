import MinRequest from './MinRequest';
const minRequest = new MinRequest();
minRequest.setConfig((config) => {
  config.baseURL = 'https://somecute.cn'
  return config
})
export default {
  // 这里统一管理api请求
  apis: {
	//首页推荐接口
    indexRecommad(data) {
      return minRequest.post('/picture/recommend',data);
    },
	//首页搜索接口
	indexCatSearch(data){
		return minRequest.post('/cat/search',data);
	},
	indexFlowerSearch(data){
		return minRequest.post('/flower/search',data);
	},
	//为照片点赞
	picLike(data){
		return minRequest.post('/picture/like',data)
	},
	//猫猫详情
	catDetail(data){
		return minRequest.post('/cat/catdetail',data);
	},
	//花花详情
	flowerDetail(data){
		return minRequest.post('/flower/flowerdetail',data);
	},
	//猫猫分类
	catSort(){
		return minRequest.post('cat/classifycolor');
	},
	//花花分类
	//按花期分类
	flowerSortState()
	{ 
	    return minRequest.post('/flower/classifyflorescence')	
	},
	//按品种进行分类
	flowerSortVariety()
	{
	    return minRequest.post('/flower/classifyvariety')		
	},
	//返回用户喜欢的猫与花
	userLikePic(data){
		return minRequest.post('picture/user',data)
	}
  }
}
// 接口使用方法
// Request () {
// 		this.$minApi.indexRecommand({data}).then(res => {
// 			this.res = res
// 			console.log(res)
// 		}).catch(err => {
// 			console.log(err)
// 		})
// 	}