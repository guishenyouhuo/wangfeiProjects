// JavaScript Document

function showtime(){
	var date=new Date();
	var Day = new Array(7);
	Day[0]=" 星期天 ";
	Day[1]=" 星期一 ";
	Day[2]=" 星期二 ";
	Day[3]=" 星期三 ";
	Day[4]=" 星期四 ";
	Day[5]=" 星期五 ";
	Day[6]=" 星期六 ";
	var tmp;var hour=date.getHours();
	if(hour>=0&&hour<=6)
	{
		tmp=" 凌晨 ";
	}else if(hour>6&&hour<=11)
	{
		tmp=" 上午 ";
	}else if(hour>11&&hour<=12)
	{
		tmp=" 中午 ";
	}else if(hour>=13&&hour<=18)
	{
		tmp=" 下午 ";
	}else if(hour>18&&hour<=23)
	{
		tmp=" 晚上 ";
	}
	var second=date.getSeconds();
	var mins=date.getMinutes();
	var hour=date.getHours();
	if(second<10)
	{
		second="0"+second;
	}
	if(mins<10)
	{
		mins="0"+mins;
	}
	if(hour<10)
	{
		hour="0"+hour;
	}
	
	var time;
	time=date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日 "+Day[date.getDay()]+tmp+hour+":"+mins+":"+second;

	$('#date').html(time);
	setTimeout("showtime()",1000);

}
$(function(){
	showtime();	

});
