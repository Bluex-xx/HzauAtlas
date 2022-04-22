<template>
	<view>
		<view class="header">
			<image class="header_avatar_bg" src="https://somecute.cn/img/static/1.jpeg" mode='aspectFill'></image>
			
			<view class="" v-if="userInfo.nickname">
				<image class="header_avatar" :src="userInfo.avatar" mode="aspectFill">
				
				</image>
				<view class="header_nickname">
					{{ userInfo.nickname }}
				</view>
			</view>
			
			<view class="" v-else>
				<image class="header_avatar" src="../../static/userInfo.png" mode="aspectFill"  @click="userAuthorize()"></image>
				<button class="header_nickname" @click="userAuthorize()">
					登录
				</button>
			</view>
		</view>
		
		<view class="content">
			<view @click="show=!show" class="content_title">
				<image class="like_icon" src="../../static/like.png" mode="aspectFill"></image>
				收藏
			</view>
                   <!-- 用户喜欢部分 -->
                    <view v-show="show" class="content_category">
                    	
                    <view class="tabs">
                    	<view @click="changeselect(1)">
                    		<view class="tabtitle-1">
                    			{{tab_1}}
                    		</view>
                    	</view>
                    	
                    	<view v-show="tabindex==1" class="tabline"></view>
                    		<view @click="changeselect(2)">
                    			<view class="tabtitle-2">
                    				{{tab_2}}
                    			</view>
                    		</view>
                    		
                    	<view v-show="tabindex==2" class="tabline-2"></view>
                    </view>
					
					<view v-show="loading" class="loading">
					    <image src="../../static/loading.gif" class="loading-img"></image>
					</view>
                    
                    <view class="main_photo" v-show="tabindex == 1 && !loading">
                    	<view class="photo_list" v-if="likeCatList[0].pid">
                    		<view v-for="(i,index) in likeCatList" class="photo_one" @click="todetail(i.pid)" :key='index'>
								<u--image :src="i.store" mode="aspectFill" height="183rpx" width="183rpx">
								  <template v-slot:loading>
								    <u-loading-icon color="red"></u-loading-icon>
								  </template>
								</u--image>
                    		</view>
                    	</view>
                    		
                    	<u-empty v-else
                    		text='喜欢为空'
                    		mode='favor'
                    		marginTop='150rpx'
                    	>
                    	</u-empty>
                    	
                    </view>
					
                    <view class="main_photo" v-show='tabindex == 2 && !loading'>
                    	<view class="photo_list"  v-if="likeFlowerList[0].pid && !loading" >
                    		<view v-for="(i,index) in likeFlowerList" class="photo_one" @click="todetail(i.pid)" :key='index' >
								<u--image :src="i.store" mode="aspectFill" height="183rpx" width="183rpx">
								  <template v-slot:loading>
								    <u-loading-icon color="red"></u-loading-icon>
								  </template>
								</u--image>
                    		</view>
                    	</view>
                    		
                    	<u-empty v-else
                    		text='喜欢为空'
                    		mode='favor'
                    		marginTop='150rpx'
                    	>
                    	</u-empty>
                    </view>
					</view>
					
					<image v-show="show" class="scroll_to_top" @click="scrollToTop" src="../../static/scroll_to_top.png" mode="aspectFit"></image>
					
        <view v-show="!show" class="buttons">
			<view @click="about_state=true" class="button_block">
				<image class="button_block_icon" src="https://somecute.cn/img/static/关于.png"></image>
				<view class="button_block_title">
					关于
				</view>
			</view> 
			  <view @click="contribution_state=true" class="button_block" style="top: 1100rpx;">
			  	<image class="button_block_icon" src="https://somecute.cn/img/static/投稿.png"></image>
			  	<view class="button_block_title">
			  		投稿
			  	</view>
			  </view> 	
			<button open-type='feedback' class="button_block" style="left: 398rpx;">
				<image class="button_block_icon" src="https://somecute.cn/img/static/反馈.png"></image>
				<view class="button_block_title">
					反馈
				</view>
			</button> 
			<view @click="tip()" class="button_block" style="top: 1100rpx;left: 398rpx;">
				<image class="button_block_icon" src="https://somecute.cn/img/static/赞助.png"></image>
				<view class="button_block_title">
					敬请期待
				</view>
			</view> 
		</view>
        <u-popup :show="about_state" :round="30" mode="right">
			<image src="../../static/cancel.png" class="delete_icon" @click="about_state=!about_state"></image>
			<image src="https://somecute.cn/img/static/about.jpg" mode="aspectFill" class="about"></image>
        </u-popup>
		<u-popup :show="contribution_state" :round="30" mode="right">
			<image src="../../static/cancel.png" class="delete_icon" @click="contribution_state=!contribution_state"></image>
			<image src="https://somecute.cn/img/static/contribution.jpg" mode="aspectFill" class="contribution" @click="previewImg()"></image>
		</u-popup>
		</view>
	</view>
</template>

<script>
	import api from '../../api/api.js'
	export default {
		data() {
			return {
				userInfo: {
					avatar: '',
					nickname: ''
				},
				uid: '',
				likeCatList: [{
					pid: '',
					store: ''
				}],
				likeFlowerList: [{
					pid: '',
					store: ''
				}],
				tab_1: "猫猫",
				tab_2: "花花",
				tabindex: 1,
				show: false,
				about_state: false,
				contribution_state: false,
				loading: true
			}
		},
		methods: {
			changeselect(data){
				this.tabindex=data;
				this.getLike()
			},
			todetail(data){
				uni.navigateTo({	
					url: `../../packageA/pages/detail/detail?data=${data}`
				})
			},
			scrollToTop() {
				uni.pageScrollTo({
					scrollTop: 0,
					duration: 300
				});
			},
			// 获得用户点赞的内容
			getLike() {
				this.loading = true
				
				setTimeout(()=>{
					this.likeCatList='';
					this.likeFlowerList='';
					api.userLikePic({
						uid: uni.getStorageSync('uid'),
						type: this.tabindex
					}).then(res => {
						if(this.tabindex == 1) {
							this.likeCatList = res
							uni.setStorageSync('categoryindex', 1)
						}
						else {
							this.likeFlowerList = res
							uni.setStorageSync('categoryindex', 2)
						}
						this.loading = false
					})
				},500)
				
			},
			// 获取code并传给后端
			login() {
				let that = this
				uni.login({
					provider:'weixin',
					success(res) {
						uni.setStorageSync('code', res.code)
						api.login({
							name: that.userInfo.nickname,
							profile_photo: that.userInfo.avatar,
							code: res.code
						}).then(res => {								
							uni.setStorage({
								key: 'uid',
								data: res.user.uid
							})
								
							uni.showToast({
								title: '登录成功'
							})
							that.getLike()        
						})
					},
					fail(err) {
						uni.showToast({
							title: err.errMsg,
							icon: 'error'
						})
					}
				})
			},
			// 获取微信用户授权
			userAuthorize() {
				let that = this
				uni.getUserProfile({
					desc: '登录需要获取您的用户信息',
					success(res) {
						let temp = {
							avatar: res.userInfo.avatarUrl,
							nickname: res.userInfo.nickName 
						}
						
						that.userInfo = temp
						uni.setStorage({
							key:'userInfo',
							data: temp
						})
						
						that.login()
					},
					fail() {
						uni.removeStorage({
							key: 'userInfo'
						})
						
						uni.showToast({
							title: '获取信息失败',
							icon: 'error'
						})
					}
				})
			},
			previewImg(){
				uni.previewImage({
					current: "https://somecute.cn/img/static/contribution.jpg",
					urls: ["https://somecute.cn/img/static/contribution.jpg"]
				});			
			},
			tip(){
				uni.showModal({
					content: '开发中',
					showCancel: false
				});
			},
			setLoading() {
				this.loading = true
				console.log('timeout')
				let that = this
				setTimeout(that.getLike,500)
			}
		},
		onLoad() {
			let that = this
			uni.checkSession({
				success() {
					if (!uni.getStorageSync('uid')) {
						uni.showToast({
							title: '登录已过期',
							icon: 'error'
						})
					}
					else {
						that.userInfo = uni.getStorageSync('userInfo')
						that.getLike()
					}
				},
				fail() {
					uni.showToast({
						title: '登录已过期',
						icon: 'error'
					})
				}
			})
		},
		onShow() {
			if(uni.getStorageInfoSync('uid')) {
				this.getLike()
			}
			this.about_state=false;
			this.contribution_state=false;
		}
	}
</script>

<style>
.header_avatar_bg {
	position: absolute;
	left: 0rpx;
	top: 0rpx;
	width: 750rpx;
	height: 398rpx;
	border-radius: 0px 0px 24px 24px;
	filter: blur(2px);
}

.header_avatar {
	position: absolute;
	left: 279rpx;
	top: 280rpx;
	width: 200rpx;
	height: 200rpx;
	border-radius: 173rpx;
	background-color: #FFFFFF;
}

.header_avatar image {
	width: 100%;
	height: 100%;
}

.header_nickname {
	position: absolute;
	/* left: 154px; */
	top: 500rpx;
	width: 100%;
	line-height: 65rpx;
	font-family: GenJyuuGothic-Monospace-Regular;
	font-size: 50rpx;
	text-align: center;
	font-weight: normal;
	letter-spacing: 0em;
	color: #1E1D1D;
	mix-blend-mode: multiply;
}

.header button {
	color: grey;
	background:rgba(0, 0, 0, 0);
}

button::after{
	border: none;
}

.content_title {
	background-color: #FFFFFF;
	position: absolute;
	left: 45rpx;
	right: 45rpx;
	top: 600rpx;
	line-height: 104rpx;
	border-radius: 20rpx;
	box-shadow: 0rpx 10rpx 10rpx rgba(213, 213, 213, 0.6);
	font-size: 42rpx;
	color: #9E9E9E;
	text-align: center;
}

.like_icon {
	position: absolute;
	left: 230rpx;
	top: 30rpx;
	width: 46rpx;
	height: 46rpx;
}

.content_category {
    position: relative;
    left: 45rpx;
    top: 730rpx;
    width: 659rpx;
    min-height: 46vh;
    border-radius: 10px;
    background: #FFFFFF;
}

	.tabs
	{
		position: absolute;
		position: relative;
		top:40rpx;
		left: 65rpx;
		width: 500rpx;
		height: 70rpx;

	}
	.tabtitle-1
	{
		position: absolute;
		left: 60rpx;
		top:5rpx;
		color: grey;
	}
	.tabtitle-2
	{
		position: absolute;
		left: 418rpx;
		top:5rpx;
		color: grey;
	}
	.tabline
	{
		position: absolute;
		width:80rpx;
		height:2rpx;
		left: 50rpx;
		bottom: 0;
		background: #FAACA8;
	}
	.tabline-2
    {
    	position: absolute;
    	width:80rpx;
    	height:2rpx;
    	left: 410rpx;
    	bottom: 0;
    	background: #FAACA8;
    }
	
	.main_photo
	{
		padding:60rpx 35rpx 50rpx 40rpx;
	}
	
	.photo_list
	{
		display: flex;
		flex-wrap: wrap;
	}
	
	.photo_one
	{
		width: 180rpx;
		height: 180rpx;
		margin: 6rpx;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.4);
	}
	.button_block
	{
		width: 310rpx;
		height: 310rpx;
		position: absolute;
		left: 45rpx;
		top:750rpx;
		border-radius: 20rpx;
		background: #FFFFFF;
	}
	.buttons button
	{
		border: none;
		padding: 0;
		margin: 0;
	}
	button .button_block_title:last-child
	{
		margin-top: -41rpx;
		margin-left: -5rpx;
	}
	.button_block_icon
	{       
        width: 130rpx;
        height: 130rpx;
        margin: 60rpx 80rpx 17rpx 95rpx;
	}
	.button_block_title
	{
		font-size: 35rpx;
		color: grey;
		margin-left: 123rpx;
		font-weight: 500;
	}
	.button_block:last-child view
	{
		margin-left: 90rpx;
	}
	.about
	{
		width: 760rpx;
		height: 1190rpx;
		margin-top: 10rpx;
	}
	.contribution
	{
		width: 760rpx;
		height: 1130rpx;
		margin-top: 10rpx;
	}
	.delete_icon
	{
	    width: 60rpx;
	    height: 60rpx;
        margin-left: 50rpx;
		margin-top: 100rpx;
	}
	.loading
	{
		position: absolute;
		width: 600rpx;
		left: 0rpx;
		top: 200rpx;
		padding: 80rpx 30rpx 100rpx 30rpx;
		background-color: #FFFFFF;
		border-radius: 20rpx;
	}
	.loading-img
	{
		width: 166rpx;
		height: 166rpx;
		margin: 10rpx 0rpx 0 228rpx;
	}
	.scroll_to_top {
		position: fixed;
		bottom: 30rpx;
		right: 30rpx;
		width: 100rpx;
		height: 100rpx;
		border-radius: 50rpx;
		background-color: rgba(212, 212, 212, 0.6);
	}

</style>
