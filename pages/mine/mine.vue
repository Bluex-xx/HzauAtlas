<template>
	<view>
		<view class="header">
			<image class="header_avatar_bg" src="https://somecute.cn/img/static/mine_bg.jpg" mode='aspectFill'></image>
			
			<view class="" v-if="userInfo.nickname">
				<image class="header_avatar" :src="userInfo.avatar" mode="aspectFill">
					头像
				</image>
				<view class="header_nickname">
					{{ userInfo.nickname }}
				</view>
			</view>
			
			<view class="" v-else>
				<image class="header_avatar" src="../../static/userInfo.png" mode="aspectFill"></image>
				<button class="header_nickname" @click="userAuthorize()">
					登录
				</button>
			</view>
		</view>
		
		<view class="content">
			<view class="content_title">
				<image class="like_icon" src="../../static/like.png" mode="aspectFill"></image>
				like
			</view>
		
		<view class="content_category">
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
		
			<view class="main_photo">
				<view v-for="i in like_list[tabindex-1].content" class="photo_one" @click="todetail()">
					<image src="https://somecute.cn/img/static/2.jpg" mode="aspectFill" style="width: 100%;height: 100%;"></image>
					<!-- {{ i }} -->
				</view>
			</view>
			</view>
		</view>	
		</view>
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
				like_list: [{
				    name: 'Cat',
					content:[
						'1111'
					]
				}, {
				    name: 'Flower',
					content:[
						'2222'
					]
				}],
				tab_1:"猫猫",
				tab_2:"花花",
				tabindex:1
			}
		},
		methods: {
			changeselect(data){
				this.tabindex=data;
				this.getLike()
			},
			todetail(){
				uni.navigateTo({	
					url: '../../packageA/pages/detail/detail'
				})
			},
			getLike() {
				api.userLikePic({
					uid:'001',
					type:this.tabindex
				}).then(res => {
					this.like_list[this.tabindex-1].content = res
				})
			},
			
		},
		onLoad() {
			// this.userInfo = uni.getStorageSync('userInfo')
			// this.userAuthorize()
			// uni.checkSession({  
			// 	success(res) {
			// 		console.log(res)
			// 	},
			// 	fail(err) {
			// 		console.log(err)
			// 	}
			// })
			
			// if(uni.getStorageSync('token')) {
			// 	console.log('has token')
			// }
			// else {
			// 	this.login()
			// }
			// this.getLike()			
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
	/* box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3); */
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

.content_title {
	background-color: #FFFFFF;
	position: absolute;
	left: 45rpx;
	right: 45rpx;
	top: 600rpx;
	line-height: 104rpx;
	border-radius: 20rpx;
	box-shadow: 0rpx 10rpx 10rpx rgba(213, 213, 213, 0.6);
	font-size: 46rpx;
	color: #9E9E9E;
	text-align: center;
}

.like_icon {
	position: absolute;
	left: 240rpx;
	top: 30rpx;
	width: 46rpx;
	height: 46rpx;
}

.content_category {
	position: relative;
	left: 34rpx;
	top: 720rpx;
	width: 680rpx;
	/* height: 700rpx; */
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
		display: flex;
		flex-wrap: wrap;
		padding:60rpx 45rpx 50rpx 45rpx;
	}
	.photo_one
	{
		width: 183rpx;
		height: 183rpx;
		margin: 6rpx;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.4);
	}
	
</style>
