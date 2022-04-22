<template>
	<view class="content">
		<view class="detail_banner">
			        <u-swiper
			                :list="pictureUrlList"
			                previousMargin="0"
			                nextMargin="0"
			                circular
			                :autoplay="true"
			                bgColor="#ffffff"
							height="550rpx"
							interval="3999"
			        ></u-swiper>
		</view>
		<image @click="backToLast" src="../../../static/back-icon.png" class="back"></image>
        <!-- 猫猫信息 -->
        <view class="cat_information_bg">
        	<view class="cat_title">
        		{{title}}
        	</view>
			<view class="title_line"></view>
			<image @click="islike()" v-show="!list.pictureList[0].islike" src="../../../static/heart-icon.png" class="love"></image>
		    <image @click="islike()" v-show="list.pictureList[0].islike" src="../../../static/heart-icon-selected.png" class="love"></image>
			<button  class="share" type="default" open-type="share"></button>
			<view class="main_text">
		    <!-- 骨架加载 -->
			<u-skeleton
				    rows="3"
					:loading="loading"
			></u-skeleton>	
			<view v-if="!loading">
			<view class="cat_name">
				<image src="../../../static/name-icon.png" class="name_icon"></image>
				<view>
					大名:{{list.name}}
				</view>
			</view>
			<view class="cat_name">
				<image src="../../../static/name-icon.png" class="name_icon"></image>
				<view v-show="tabindex==1">
					性格:{{list.character}}
				</view>
				<view v-show="tabindex==2">
					品种:{{list.department}}
				</view>
			</view>
			<view class="cat_body">
				<image src="../../../static/body-icon.png" class="body_icon"></image>
				<view v-show="tabindex==1">
					颜色:{{list.color}}
				</view>
				<view v-show="tabindex==2">
					花期:{{list.florescence}}
				</view>
			</view>
			<view class="cat_location">
				<image src="../../../static/location-icon.png" class="location_icon"></image>
				<view v-show="tabindex==1">
					出没地点:{{list.place}}
				</view>
				<view v-show="tabindex==2">
					花语:{{list.allegory}}
				</view>
			</view>
			</view>
			</view>
        </view>
        <!-- 猫猫相册 -->
		<view class="cat_photos">
		<view class="cat_title">
			{{photo_title}}
		</view>
		<view class="title_line" style="width: 130rpx;"></view>	
		<view v-if="loading" class="main_photo">
			<!-- 骨架加载 -->
				<u-skeleton
					    rows="5"
						:loading="loading"
				></u-skeleton>	
		</view>
		<view v-if="!loading" class="main_photo">
			<view v-for="(i,index) in list.pictureList" :key="index" class="photo_one" @click="previewImg(i.store)">
				<u--image :src="i.store" mode="aspectFill" height="183rpx" width="183rpx">
				  <template v-slot:loading>
				    <u-loading-icon color="red"></u-loading-icon>
				  </template>
				</u--image>
			</view>
		</view>
		</view>
        <!-- 评论部分 -->
		<view class="comments">
		<view class="cat_title">评论区</view>
		<view class="title_line" style="width: 130rpx;"></view>	
		<view class="comment_button" @click="comment()"><u-icon name="chat" color="#ffffff" size="50rpx"></u-icon></view>
		<view v-for="(i,index) in commentList" :key="index" class="comment">
             <image :src="i.profile_photo" mode="aspectFill" class="comment_img">
             </image>
			 <view class="comment_name">{{i.name}}</view>
			 <view class="comment_value">
		 	{{i.content}}
			 </view>
			 <view @click="commentDelete(i.id,index)" class="comment_delete">
			 	删除
			 </view>
			 <view class="comment_data">
			 	{{i.date}}
			 </view>
		</view>
        <!-- 评论表单弹出层 -->
		<u-popup :show="show" :round="10" mode="bottom">
				<view class="cat_title" style="left: 335rpx;top:40rpx">评论</view>
				<view class="title_line" style="width: 130rpx;left:318rpx;top:120rpx;"></view>	
				<view class="comment_inputbox">
		              <u--input
		                placeholder="请输入内容(50字内)"
		                border="surround"
		                v-model="value"
		                @change="change"
						maxlength=50
						type="text"
						showWordLimit="true"
						clearable
		              ></u--input>
				</view>
				<view @click="commentPost()" class="comment_post_button">Post</view>
				<image src="../../../static/cancel.png" class="delete_icon" @click="comment"></image>
		</u-popup>
		<view class="comment_empty" v-if="commentList==''">
		<u-empty
		        mode="comment"
		        icon="http://cdn.uviewui.com/uview/empty/comment.png"
		>
		</u-empty>
		</view>
		</view>
	</view>
	

</template>

<script>
import api from '@/api/api.js'
export default {
		data() {
			return {
				title:"猫猫信息",
				photo_title:"照片集",
				pid:"",
				list:"",
				tabindex:"",
				commentList:"",
				loading:true,
				show:false,
				value:"",
				uid:1,
				pictureUrlList:[]
			}
		},
		methods: {
			// 返回上一页面
            backToLast(){
				let pages = getCurrentPages()
				if(pages[pages.length-2]) {
					uni.navigateBack({
						url:pages[pages.length-2].route
					})
				}
				else {
					uni.switchTab({
						url: '../../../pages/index/index'
					})
				}
			},
			toUser(){
				uni.switchTab({
					url: '../../../pages/mine/mine'
				});
			},
			//照片集放大预览
			previewImg(data){
			uni.previewImage({
				// 当前需要预览的图片
				current:""+data,
				//所有图片
				urls:this.pictureUrlList
			});			
			},
			//点赞函数
			async islike(){
				if(this.uid=="1")
				{
					await uni.showModal({
					   	content: '请先登录',
					   	showCancel: false
					   });	
					this.toUser();
				}
				else
				{
					api.picLike({uid:this.uid,pid:this.list.pictureList[0].pid}).then(
					res => {
							if(res=="操作成功")
							{
								this.list.pictureList[0].islike=!this.list.pictureList[0].islike;
							}
							}).catch(err => {
								console.log(err)
							})
				}
			},
			async comment(){
				if(this.uid==1)
				{
					await uni.showModal({
					   	content: '请先登录',
					   	showCancel: false
					   });
					this.toUser();	   
				}
				else
				{
					 this.show=!this.show;
				}
			},
			//评论提交接口
			commentPost(){
				if(this.value=="")
				{
					uni.showModal({
						content: '请输入后提交评论',
						showCancel: false
					});
				}
				else
				{
					if(this.tabindex==1)
					{
						api.comment({uid:this.uid,content:this.value,cid:this.list.cid}).then(
						res => {
								this.commentList = res;
								for(let i of this.commentList)
								{
								   i.profile_photo=i.user.profile_photo;
								   i.name=i.user.name;
								}
								this.show=false;
								this.value="";
								uni.showModal({
									content: '评论成功',
									showCancel: false
								});
								}).catch(err => {
									console.log(err)
									uni.showModal({
										content: '评论失败',
										showCancel: false
									});
								})
					}
					else
					{
						api.comment({uid:this.uid,content:this.value,fid:this.list.fid}).then(
						res => {
								this.commentList = res;
								for(let i of this.commentList)
								{
								   i.profile_photo=i.user.profile_photo;
								   i.name=i.user.name;
								}
								this.show=false;
								this.value="";
								uni.showModal({
									content: '评论成功',
									showCancel: false
								});
								}).catch(err => {
									console.log(err)
									uni.showModal({
										content: '评论失败',
										showCancel: false
									});
								})
					}
				}
			},
			//删除评论
			async commentDelete(id,index){
				if(this.uid==1)
				{
					await uni.showModal({
					   	content: '请先登录',
					   	showCancel: false
					   });	
					this.toUser();
				}
				else
				{
					uni.showModal({
						content: '确认删除吗',
						success: (res) => {
							if (res.confirm) {
								api.commentDelete({uid:this.uid,id:id}).then(
								response => {
								      if(response==1)
									  {
										  uni.showModal({
										  	content: '删除成功',
										  	showCancel: false
										  });
										  this.commentList.splice(index,1);
									  }
									  else
									  {
										  uni.showModal({
										  	content: '不是你发表的噢，无法删除',
										  	showCancel: false
										  });
									  }
								
									})
									.catch(err => {
										console.log(err)
									})
							} else if (res.cancel) {
								
							}
						}
					});
				}
			}
		},
		//处理初始化数据
		onLoad: function (option) {
			this.uid=uni.getStorageSync('uid')? uni.getStorageSync('uid'):"1";
			const pages = getCurrentPages()
			if(	pages[pages.length-2]) {
				if(pages[pages.length-2].route == 'packageA/pages/list/list') {
					this.tabindex = uni.getStorageSync('categoryindex')
				}
				else if(pages[pages.length-2].route == 'pages/mine/mine') {
					this.tabindex = uni.getStorageSync('categoryindex')
				}
				else {
					this.tabindex = option.tabindex;
				}
			}
			else {
				this.tabindex = option.tabindex
			}
		        
			if(this.tabindex==1)
			{
				//获取猫咪详情
				api.catDetail({uid:this.uid,pid:option.data}).then(
				res => {
					this.list = res;
					this.commentList=res.commentList;
					this.loading=false;
					for(let i of res.pictureList)
					{
						this.pictureUrlList.push(i.store);
					}
					}).catch(err => {
						console.log(err)
					})
			}
            else {
				this.title = '花花信息'
				//获取花花详情
				api.flowerDetail({uid:this.uid,pid:option.data}).then(
				res => {
					this.list = res;
					this.loading=false;
					this.commentList=res.commentList;
					for(let i of res.pictureList)
					{
						this.pictureUrlList.push(i.store);
					}
					}).catch(err => {
						console.log(err)
					})
			}
		},
		// 分享
		onShareAppMessage(res) {
			if(res.from === 'button') {
				console.log(res.target)
			}
			return {
				title: '狮山图tututu鉴',
				path: `/packageA/pages/detail/detail?data=${this.listpictureList[0].pid}&tabindex=${this.tabindex}`,
				imageUrl: this.list.pictureList[0].store
			}
		}
	}
</script>

<style scoped>
	.content
	{
		width: 100%;
	}
	/* 详情页banner */
	.detail_banner
	{
		width: 750rpx;
		height: 550rpx;
	}
	/* 猫猫信息 */
	.cat_information_bg
	{

		position: relative;
		left: 42.24rpx;
		top: -100rpx;
		width: 666rpx;
		border-radius: 38.4rpx;
		background: #FFFFFF;
	}
	.cat_title
	{
        position: absolute;
		left: 50rpx;
		top:30rpx;
		width: 128px;
		height: 34px;
		font-size: 24px;
		font-weight: normal;
		letter-spacing: 0em;
		color: #212121;
	}
	.title_line
	{
		position: absolute;
		left: 50rpx;
		top: 100rpx;
		width: 175rpx;
		transform: rotate(180deg);
		border: 1px solid #FAACA8;
	}
	.main_text
	{
		width: 90%;
		padding: 140rpx 0 30rpx 40rpx;
	}
	.love
	{
		position: absolute;
		right: 40rpx;
		top:35rpx;
		width:50rpx;
		height: 50rpx;
	}
	.share
	{
		position: absolute;
		right: 110rpx;
		top:37rpx;
		width:50rpx;
		height: 50rpx;
		background-image: url(../../../static/share-icon.png);
		background-repeat: no-repeat;
		background-color: rgba(74, 255, 204, 0.0);
	}
	button::after {
		border: none;
	}
	.back
	{
		position: fixed;
		top: 70rpx;
		left: 35rpx;
		width: 50rpx;
		height: 50rpx;
		padding: 10rpx;
		border-radius: 20rpx;
		background: rgba(0, 0, 0, 0.3);
		border: solid #C8C7CC 1px;
		z-index: 99999;
	}
	.cat_name,.cat_body,.cat_location
	{
		color: #9E9E9E;
		font-size: 30rpx;
		font-weight: 400;
		word-wrap:break-word;
		margin-bottom: 20rpx;
		letter-spacing: 2rpx;
	}
	.name_icon,.body_icon,.location_icon
	{
		height: 45rpx;
		width: 45rpx;
		float: left;
		padding-right:7rpx;
	}
    .cat_photos
	{
		position: relative;
		left: 42.24rpx;
		top: -60rpx; 
		width: 666rpx;
		border-radius: 38.4rpx;
		background: #FFFFFF;
		min-height: 200rpx;
	}
	.main_photo
	{
		display: flex;
		flex-wrap: wrap;
		padding:130rpx 40rpx 50rpx 40rpx;
	}
	.photo_one
	{
		width: 183rpx;
		height: 183rpx;
		margin: 6rpx;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.4);
	}
/* 	评论部分 */
	.comments
	{
		position: relative;
		top: -15rpx;
		left: 43rpx;
		width: 616rpx;
		background-color: #FFFFFF;
		border-radius: 30rpx;
		padding: 18% 0 3% 6%;
		margin-bottom: 100rpx;
	}
	.comment
	{
		position: relative;
		margin-bottom: 30rpx;
		min-height: 136rpx;
		width: 91%;
		border-bottom: #c0c0c0 dashed  1px;
	}
	.comment_img
	{
		width: 101rpx;
		height: 101rpx;
		top: 7rpx;
		border-radius: 20rpx;
		position: absolute;
	}
	.comment_name
	{
		font-size: 28rpx;
		color: #FAACA8;
		margin: 7rpx;
		position: absolute;
		left: 117rpx;
		top: 4rpx;
		width: 40%;
		height: 38rpx;
		overflow: hidden;
	}
	.comment_value
	{
		font-size: 30rpx;
		padding-left: 123rpx;
		padding-top: 61rpx;
		color: #C0C0C0;
		width: 80%;
		text-wrap: wrap;
		margin-bottom: 30rpx;
		display: block;
	}
	.comment_data
	{
		font-size: 27rpx;
		position: absolute;
		top:10rpx;
		right: 0;
		width: 150rpx;
		height: 32rpx;
		overflow: hidden;
		color: #C0C0C0;
	}
	.comment_delete
	{
		font-size: 27rpx;
		position: absolute;
		top:10rpx;
		right: 62rpx;
		width: 150rpx;
		height: 34rpx;
		overflow: hidden;
		color: #4a4646;
		display: none;
	}
	.comment:hover .comment_delete
	{
		display: inline-block;
	}
	.comment_button
	{
        background: #FAACA8;
        border-radius: 20rpx;
        position: absolute;
        right: 50rpx;
        top: 34rpx;
        padding: 6rpx 37rpx;    
	}
	.comment_inputbox
	{
		width: 80%;
		margin:25% 10% 3% 10%;
	}
	.comment_post_button
	{
		width: 80%;
		margin: 5% 10% 10% 10%;
		line-height: 80rpx;
		text-align: center;
		background: #FAACA8;
	    text-align: center;
		font-size: 43rpx;
		border-radius: 20rpx;
		margin-bottom: 70rpx;
		color: #FFFFFF;
	}
	.comment_post_button:hover
	{
		background: #faaca8a3;
	}
	.delete_icon
	{
        width: 60rpx;
        height: 60rpx;
        margin-left: 351rpx;
        margin-bottom: 50rpx;    
	}
	.comment_empty
	{
      margin-left: -30rpx;
	}
</style>