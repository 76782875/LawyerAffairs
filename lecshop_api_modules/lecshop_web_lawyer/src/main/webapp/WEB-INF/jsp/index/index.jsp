<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>首页</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views toolbar-through">
  <input type="hidden" id="basePath" value="<%=basePath %>">
  <div class="page">
    <div class="page-content">
      <div style="text-align:center; padding-top:1em">
        <!--<i class="f7-icons" style="font-size:8em;margin:0 auto;color:#ccc">person_fill</i>-->
        <img class="profile-pic" id="url" src=""/>
      </div>
      <div class="list-block">
        <ul>
          <li>
            <div class="item-content">
              <div class="item-media">
                <svg class="home_svg" viewBox="0 0 1024 1024"><path d="M512 585.602322c-69.607334 0-135.048789-27.106358-184.26777-76.326363s-76.326363-114.660436-76.326363-184.26777 27.106358-135.048789 76.326363-184.26777 114.660436-76.326363 184.26777-76.326363c69.607334 0 135.048789 27.106358 184.26777 76.326363s76.326363 114.660436 76.326363 184.26777-27.106358 135.047766-76.326363 184.26777S581.607334 585.602322 512 585.602322zM512 133.487224c-105.6052 0-191.520965 85.915765-191.520965 191.520965s85.915765 191.520965 191.520965 191.520965c105.6052 0 191.520965-85.915765 191.520965-191.520965S617.6052 133.487224 512 133.487224zM888.761899 962.365244c-19.074428 0-34.536584-15.462157-34.536584-34.536584 0-188.703803-153.521512-342.226339-342.226339-342.226339S169.773661 739.123834 169.773661 927.82866c0 19.074428-15.462157 34.536584-34.536584 34.536584s-34.536584-15.462157-34.536584-34.536584c0-55.509245 10.879792-109.37813 32.336476-160.106488 20.717857-48.981574 50.369178-92.964298 88.131223-130.72532 37.761022-37.762045 81.743746-67.414389 130.72532-88.131223 50.729381-21.456684 104.597243-32.336476 160.106488-32.336476 55.509245 0 109.37813 10.879792 160.106488 32.336476 48.981574 20.716834 92.964298 50.369178 130.726344 88.131223 37.761022 37.761022 67.412342 81.743746 88.1302 130.72532 21.456684 50.728358 32.336476 104.597243 32.336476 160.106488C923.298484 946.902064 907.836327 962.365244 888.761899 962.365244z"></path></svg>
              </div>
              <div class="item-inner">
                <div class="item-title">姓名</div>
                <div id="name" class="item-after"></div>
              </div>
            </div>
          </li>
          <li>
            <div class="item-content">
              <div class="item-media">
                <svg class="home_svg" viewBox="0 0 1024 1024"><path d="M511.999488 90.959641c-232.538147 0-421.039335 188.503235-421.039335 421.041382 0 232.536101 188.501188 421.038312 421.039335 421.038312 232.53917 0 421.039335-188.502212 421.039335-421.038312C933.038824 279.462876 744.538659 90.959641 511.999488 90.959641L511.999488 90.959641zM594.378707 705.283098c-0.906649 1.592264-22.716374 38.935778-75.28566 76.435856-0.607844 0.748037-1.252527 1.449002-2.003634 1.992377-0.986467 0.753153-2.055822 1.161453-3.14462 1.396813-0.926092 0.340761-1.851161 0.566912-2.796696 0.566912-1.694595 0-3.39226-0.566912-4.90266-1.728364-49.51574-37.950334-75.150584-76.549443-76.200496-78.172407-1.705851-2.589988-2.5071-5.970992-2.188852-9.292644 9.621125-102.21601 49.822732-272.040836 58.745962-309.004703-28.761044-39.791262-38.629809-102.6898-39.040155-105.46603-0.997724-6.597255 2.362814-13.003152 7.544837-14.379499 1.296529-0.360204 31.423687-8.253988 57.08002-8.253988 25.668613 0 55.620785 7.943926 56.897871 8.253988 5.179976 1.39886 8.53028 7.791453 7.525394 14.379499-0.420579 2.796696-10.321066 65.685002-39.114856 105.46603 8.922207 36.9434 49.063439 206.942189 58.665121 309.139779C596.444762 699.69073 595.817476 702.793394 594.378707 705.283098L594.378707 705.283098z"></path></svg>
              </div>
              <div class="item-inner">
                <div class="item-title">律所</div>
                <div id="lawyersPlace" class="item-after"></div>
              </div>
            </div>
          </li>
          <li>
            <div class="item-content">
              <div class="item-media">
                <svg class="home_svg" viewBox="0 0 1024 1024"><path d="M407.6 618c80.3 79.9 173.4 156.4 209.9 119.4 52.3-52.8 84.5-98.7 200.5-6 116 92.8 27.4 155.3-23.2 206.4-58.5 59-277.3 4.1-494.2-211.6-217-215.9-273.5-434.8-215-493.8 50.7-51.1 112.3-140.4 205.7-24.8 93.4 115.6 47.8 148.2-4.4 201C250.4 445.4 327.3 538 407.6 618m116.5-381.5s-24.2-3.8-41.2 13.3c-17.5 17.5-18.2 47.6 0.1 66.1 10.9 10.9 26.2 13.2 26.2 13.2 29.9 5.6 75 15.2 123 63.2 48 48.1 57.5 93.2 63.1 123.2 0 0 2.4 15.4 13.2 26.3 18.4 18.4 48.5 17.7 66 0.2 17-17 13.2-41.4 13.2-41.4-9.5-60.7-38.9-124.1-89.3-174.6s-113.7-79.9-174.3-89.5m227.5 36.4C839.5 360.9 877 461 864.8 542c0 0-4.2 25.7 12.5 42.5 18.8 18.9 48.7 17.8 66.3 0.2 11.3-11.4 13.8-29.8 13.8-29.8 10.1-70.1-2.6-210.9-139.6-348.2-137-137.3-277.6-150-347.4-139.9 0 0-18.4 2.5-29.7 13.8-17.6 17.7-18.7 47.6 0.2 66.5 16.7 16.8 42.3 12.5 42.3 12.5 80.6-12.5 180.4 25.1 268.4 113.3m0 0"></path></svg>
              </div>
              <div class="item-inner">
                <div class="item-title">电话</div>
                <div id="mobile" class="item-after"></div>
              </div>
            </div>
          </li>
          <li>
            <div class="item-content">
              <div class="item-media">
                <svg class="home_svg" viewBox="0 0 1024 1024"><path d="M512 1024C512 1024 73.142857 648.777143 73.142857 417.938286 73.142857 187.099429 269.604571 0 512 0 754.395429 0 950.857143 187.099429 950.857143 417.938286 950.857143 648.777143 512 1024 512 1024ZM512 292.571429C431.177143 292.571429 365.714286 358.034286 365.714286 438.857143 365.714286 519.68 431.177143 585.142857 512 585.142857 592.822857 585.142857 658.285714 519.68 658.285714 438.857143 658.285714 358.034286 592.822857 292.571429 512 292.571429Z"></path></svg>
              </div>
              <div class="item-inner">
                <div class="item-title">地址</div>
                <div id="address" class="item-after"></div>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="card home_card">
        <div class="card-header">今日盈利</div>
        <div class="card-content">
          <div class="card-content-inner"><h3 style="font-size: 2em;margin: 0">¥ <span id="todayIncome"></span></h3></div>
        </div>
      </div>
      <div class="card home_card">
        <div class="card-header">总计盈利</div>
        <div class="card-content">
          <div class="card-content-inner"><h3 style="font-size: 2em;margin: 0">¥ <span id="allIncome"></span></h3></div>
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="../common/foot.jsp">
    <jsp:param name="from" value="index"/>
  </jsp:include>

</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/index/index.js"></script>

</html>