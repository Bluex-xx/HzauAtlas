<template>
	<view>
		<view class="header">
			<image class="header_avatar_bg" src="../../static/timg1.jpg"></image>
			
			<view class="" v-if="userInfo">
				<image class="header_avatar" :src="userInfo.avatar" mode="aspectFill">
					头像
				</image>
				<view class="header_nickname">
					{{ userInfo.nickname }}
				</view>
			</view>
			
			<view class="" v-else>
				<image class="header_avatar" src="../../static/userInfo.png" mode="aspectFill"></image>
				<view class="header_nickname">
					昵称
				</view>
			</view>
		</view>
		
		<view class="content">
			<view class="content_title">
				<image class="like_icon" src="../../static/like.png" mode="aspectFill"></image>
				like
			</view>
			
			<!-- <view class="content_category">
			<template class="">
				<u-sticky bgColor="#aaaaff">
				<u-tabs
					:list="like_list"
			        lineWidth="30"
			        lineColor="#FAACA8"
			        :activeStyle="{
			  			color: '#303133',
			  			fontWeight: 'bold',
			  			transform: 'scale(1.05)'
			  		}"
			        :inactiveStyle="{
			  			color: '#706868',
			  			transform: 'scale(1)'
			  		}"
			        itemStyle="padding-left: 15px; padding-right: 15px; height: 34px;"
			      >
			    </u-tabs>
				</u-sticky>
			</template>
		</view> -->
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
		
			
			<!-- <view class="cat_list">
				<view v-for="i in 8" class="cat_one">
						<image @click="todetail" src="../../static/cat_img/2.jpg" class="cat_one_image" mode="aspectFill"></image>
				</view>
			</view> -->
		
			<view class="main_photo">
				<view v-for="i in like_list[tabindex-1].content" class="photo_one" @click="todetail()">
					<image src="../../static/cat_img/1.jpg" mode="aspectFill" style="width: 100%;height: 100%;"></image>
					{{ i }}
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
					avatar: '../../static/20210927224434.jpg',
					nickname: 'bluexx'
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
			}
		},
		mounted() {
			this.getLike()
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
