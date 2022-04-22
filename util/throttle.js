export const throttle = (fn, delay = 1000) => {    
    let lastTime = 0, timer = null
   
    return function () {
       let _this = this
       let _arguments = arguments
       let now = new Date().getTime()
       clearTimeout(timer)
       // 判断上次触发的时间和本次触发的时间差是否小于delay,创建一个timer
       if (now - lastTime < delay) {
			timer = setTimeout(function () {
				lastTime = now
				console.log("执行器触发")
				fn.apply(_this, _arguments)
			}, delay)
		} 
		else {
         // 否则可以直接执行
			lastTime = now
			console.log("直接触发")
			fn.apply(_this, _arguments)
		}
     }
}
