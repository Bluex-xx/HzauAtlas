<template>
	<view class="content">
		<image @click="backToLast" src="../../../static/back-icon.png" class="back"></image>
		<view class="science_banner">
			<image src="../../../static/science-bg.png" mode="aspectFill" style="height: 100%;width: 100%;"></image>
		</view>
		<view class="science_main">
			<view class="science_title">
				{{title}}
			</view>
			<view class="title_line"></view>
			<view class="science_text">
				{{text_1}}
				<view class="mini_title">
					<image src="../../../static/food-icon.png"></image>
					猫粮
				</view>
				{{text_2}}
				<view class="mini_title">
					<image src="../../../static/can-icon.png"></image>
					罐头
				</view>
				{{text_3}}
				<view class="mini_title">
					<image src="../../../static/snack-icon.png"></image>
					小吃
				</view>
			    {{text_4}}
				<view class="mini_title">
					<image src="../../../static/other-icon.png"></image>
					其他
				</view>
				{{text_5}}
			</view>
			<view class="pku">
				指南参考：北大猫咪图鉴
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				title:"投喂指南",
				text_1:"人常见的食物比如巧克力、葡萄制品、大葱洋葱对猫有毒，可能会引起急性肾衰竭，切不可以喂食！此外，人们常用来喂猫的火腿肠和牛奶也并不适合猫咪，会引起肠胃反应，造成腹泻等状况。酸性的各类水果、香辛料制的肉类、乌贼等海产也均不适合猫咪食用。动物一般从食材本身中获取的盐分已经足够无需像人类一样额外摄入大量盐分，猫也在此列。所以直接给猫喂食剩饭剩菜或者厨余配料对猫来说并不合适，且不能提供全面的营养"
+"还会对口腔和肾脏造成负担。长期食用含盐食"
+"物，可能导致猫咪患上口炎、肾病，进而导致"
+"营养不良乃至死亡。"
+"猫常见的较合适的食物有猫粮、罐头、以及其"
+"它一 些小吃（具体种类见下）。尤其要注意控"
+"制盐分的摄入，以免造成对猫肾脏的过重负担",
                text_2:"猫粮作为猫的主要食物来源，可以给猫提供均衡的营养，水煮鸡胸肉、水煮鸡肝之类清淡食物也非常适合作为主食为猫咪加餐。选择猫粮时需注意成分表，避免喂食盐含量高（在成分表中位置靠前）的猫粮。",
			    text_3:"罐头主要是一些肉类的混合物，虽然不及鲜肉新鲜美味，但较为简便，是一种常见的肉类补充物质。不过需要注意的是猫罐头分为两种，圭粮罐与补充罐（或叫零食罐、附加罐等）补充罐不能代替猫粮，其在营养上有所不足。大品牌的主粮罐可以作为主粮使用，小品牌的大多宣称自己是主粮罐但是实际上并未达到标准，因此不建议使用。",
				text_4:"较为常见的小吃有妙鲜包、鲜封包、猫布丁等等，里面包含一些肉块和小块鱼虾之类，比罐头差了许多，一般用于奖励和行为训练，不建议长期使用。还有肉干肉条和鸡胸肉等封装的肉类，猫也很喜欢。",
				text_5:"需要介绍的还有猫麦草和猫薄荷。猫麦草可以提供一些粗纤维，可以帮助猫排出毛球、缓解肠胃不适，因此看见猫咪吃草不必惊讶哦。而猫薄荷可以引起猫咪兴奋，也被称为“猫咪毒品”，不过不会成瘾，一般用来与猫玩耍时使用，但有些猫兴奋后会表现出更强烈的攻击性，需要小心。另外需要注意猫薄荷可能引起猫咪的轻度幻觉，因此需要在安全环境下使用，同时不建议长期频繁使用。另外，不建议给半岁以下的幼猫喂食肉食。幼猫肠胃功能较弱，过早开始吃肉食容易引起拉稀。如需进行行为训练，建议使用干粮或者奶布丁。切勿给末断奶的幼猫喂食牛奶，应喂食幼猫专用奶粉，专用奶粉难以购买时也可以暂时使用舒化奶 （不含乳糖）替代。"
			}
		},
		methods:{
			backToLast(){
				let pages = getCurrentPages()
				uni.navigateBack({
					url:pages[pages.length-2].route
				})
			},
		}
	}
</script>

<style scoped>
	.content
	{
		width: 100%;
	}
	.science_banner
	{
		width: 750rpx;
		height: 450rpx;
		border-radius: 0 0 80rpx 80rpx;
		overflow: hidden;
	}
	.back
	{
		position: fixed;
		top: 95rpx;
		left: 35rpx;
		width: 50rpx;
		height: 50rpx;
		padding: 10rpx;
		border-radius: 20rpx;
		background: rgba(0, 0, 0, 0.3);
		border: solid #C8C7CC 1px;
		z-index: 99;
	}
	.science_main
	{
		position: relative;
		top:-130rpx;
		left: 45rpx;
		width: 606rpx;
		border-radius: 20rpx;
		padding: 30rpx;
		background: #ffffff;
	}
	.science_title
	{
	    position: absolute;
		left: 235rpx;
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
		left: 230rpx;
		top: 100rpx;
		width: 190rpx;
		transform: rotate(180deg);
		border: 1px solid #FAACA8;
	}
	.mini_title
	{
		font-size: 35rpx;
		color: #000000;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		font-weight: 500;
		margin-left: 225rpx;
	}
	.mini_title image
	{
		margin-right: 4rpx;
		width: 35rpx;
		height: 35rpx;
	}
	.science_text
	{
		padding:110rpx 10rpx 50rpx 30rpx;
	    letter-spacing: 5rpx;
		word-wrap: break-word;
		font-size: 30rpx;
		line-height: 40rpx;
		color: grey;
		font-weight: 300;
	}
	.pku
	{
		position: absolute;
		font-size: 20rpx;
		bottom: 20rpx;
		right: 30rpx;
		font-weight: 200;
	}
</style>
