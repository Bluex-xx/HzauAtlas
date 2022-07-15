const baseUrl = 'https://127.0.0.1/'//填写java服务部署的服务器地址

var tokenStr;

export default async (url = '', data = {}, type = 'GET') =>  {
				tokenStr = uni.getStorageSync('token')
				
				const [err, res] = await uni.request({
					url: baseUrl+url,
					method:type,
					data: data,
					header:{
						Authorization : tokenStr
					}
				});
				if (err) {
					uni.showModal({
						content: err.errMsg,
						showCancel: false
					});
					return(false)
				} else {
					if(res.statusCode === 401){
						uni.showToast({
							title:"身份过期，请重新登陆！",
							duration:2000,
							icon:"none"
						})
						uni.redirectTo({
							url:"/pages/mine/mine"
						})
					}
					else if(res.statusCode === 403){
						uni.showToast({
							title:"没有操作权限，请联系上级管理员",
							duration:2000,
							icon:"none"
						})
					}
					else{
						let	request = res.data
						return(request)
					}					
				}
				
			}

export { baseUrl }
