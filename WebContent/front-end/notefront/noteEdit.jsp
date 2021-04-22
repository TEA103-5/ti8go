<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.note.model.*"%>


<%
  NoteVO noteVO = (NoteVO) request.getAttribute("noteVO");
%>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Blog Post - font-end</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/css/smoothproducts.css">
    <link href="<%=request.getContextPath()%>/front-end/notefront/assets/css/blog-home.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/jquery.min.js"></script>
    <script src="//cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

</head>
<style>
    #preview{
        width: 200px;
        height: 200px;
    }
    #preview_image {
    max-width: 100%;
  }
  
  .swal2-html-container{
 color: white;  /*讓undefined消失*/
 }
  .error-msg{
 color: red;
 }
</style>
<body>
    <%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font class="error pt-5"style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/note/note.do" name="form1">
  <div class="container">

    <div class="row mt-5 mb-5 ">
  <!-- Title -->
  <div class="col-lg-8">
    <h1 class="edit">新增遊記</h1>
  </div>
      <!-- Post Content Column -->
      <div class="col-lg-8 mt-3">
        
          <div class="form-group">
            <label for="exampleFormControlInput1">標題</label>
            <input type="text" name="note_title" class="form-control" id="exampleFormControlInput1" value="${noteVO.note_title}"><span id="title1" style="color:red"></span>
          </div>
          
          <div class="form-group">
            <label for="exampleFormControlInputdate1">旅遊日期</label>
            <input name="travel_start" class="form-control" id="f_date1" type="text" value="${noteVO.travel_start}">
          </div>
          
          <div class="form-group">
            <label for="exampleFormControlInput1">大綱</label>
            <textarea type="text" name="note_description" class="form-control" id="Input2" value="">${noteVO.note_description}</textarea>
          </div>
		<script type="text/javascript">
		  $(document).ready(function() {
		  	  CKEDITOR.replace( 'Input2' );
              var $CKcontent = CKEDITOR.instances.Input2.getData();
		  })
 		</script>

          <div class="bd pt-5">
          <button type="submit" class="btn btn-primary1" id="btn_submit">送出</button>
          </div>
        
        </div>

        

      </div>

    </div>
    <!-- /.row -->
  </div>
  <!-- /.container -->
  <br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="note_classid" value="1">
<input type="hidden" name="trip_id" value="${(tripVO==null)?1:(tripVO.trip_id)}">
<!-- classid預設1 -->
<input type="hidden" name="users_id" value="${users_id}">
<input type="hidden" name="note_like" value="0">

  </FORM>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  <script>
  
//錯誤驗證 sweet alert
  var all_error;

	<c:if test="${ not empty errorMsgs}">
	
	<c:forEach var="message" items="${errorMsgs}">
		var one_error = 
			`<div class="error">
				<span class="error-msg">${message}</span>
			</div>`
	
		all_error += one_error;
	
		console.log(one_error);

 	</c:forEach>
 	
 	
	  Swal.fire({
		  title: `<span class="alert-title">請修正以下錯誤</span>`,
		  icon: 'info',
		  html: all_error,
		  width: "400px",
		  margin: "0 auto",
		  showCloseButton: true,
		  showConfirmButton: true,
		  focusConfirm: true,
		})
 	
		
</c:if>
  
//         $("#btn_submit").on("click", function (e) {
//             let task_text = ($('#exampleFormControlInput1').val()).trim();
//             if (task_text == "") {
//                 e.preventDefault();
//                 $('#title1').text('*請勿空白');
//             } else {
//                 $('#title1').text('')
//             }
//         });

        
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value:   new Date()
          
        });
        // 以下為某一天之後的日期無法選擇
             var somedate2 = new Date('2021-04-28');
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() >  somedate2.getYear() || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
  </script>

    <footer class="page-footer dark" style="background: #575D59;">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <h5>Get started</h5>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Sign up</a></li>
                        <li><a href="#">Downloads</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>About us</h5>
                    <ul>
                        <li><a href="#">Company Information</a></li>
                        <li><a href="#">Contact us</a></li>
                        <li><a href="#">Reviews</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Support</h5>
                    <ul>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">Help desk</a></li>
                        <li><a href="#">Forums</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Legal</h5>
                    <ul>
                        <li><a href="#">Terms of Service</a></li>
                        <li><a href="#">Terms of Use</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <p>© 2021 Copyright Text</p>
        </div>
    </footer>
    
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/theme.js"></script>

    
</body>

</html>