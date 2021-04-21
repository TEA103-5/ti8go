<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.notec.model.*"%>


<%
  NoteCVO noteCVO = (NoteCVO) request.getAttribute("noteCVO");
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
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/notec/notec.do" name="form1" enctype="multipart/form-data">
  <div class="container">

    <div class="row mt-5 mb-5 ">
  <!-- Title -->
  <div class="col-lg-8">
    <h1 class="edit">新增內容</h1>
  </div>
      <!-- Post Content Column -->
      <div class="col-lg-8 mt-3">

		<tr>
				<td>選擇主標題 :</td>
				<td>
				<jsp:useBean id="noteSvc" scope="page"
						class="com.note.model.NoteService" />


					<FORM METHOD="post" ACTION="notec.do">
						<select class="form-control" size="1" name="note_id">
							<c:forEach var="noteVO" items="${noteSvc.all}">
							<c:if test="${sessionScope.users_id == noteVO.users_id}">
								<option value="${noteVO.note_id}">${noteVO.note_title}
							</c:if>	
							</c:forEach>
						</select> <input type="hidden" name="action" value="insert">
					</FORM>
					</td>
			</tr>
			
          <div class="form-group pt-3">
            <label for="exampleFormControlInput1">副標題</label>
            <input type="text" name="note_c_title" class="form-control" id="exampleFormControlInput3"><span id="title3" style="color:red" value="${noteCVO.note_c_title}"></span>
          </div>
          <div class="form-group">
            <label for="exampleFormControlTextarea1">內容介紹</label>
            <textarea type="text" name="note_c_content" class="form-control" id="Input2">${noteCVO.note_c_content}</textarea>
          </div>
          <script type="text/javascript">
		  	$(document).ready(function() {
		  	  CKEDITOR.replace( 'Input2' );
              var $CKcontent = CKEDITOR.instances.Input2.getData();
		 	 })
 		</script>
        
      

          <div class="form-group pt-3">
            <label for="exampleFormControlFile1">新增相片</label>
            <input type="file" name="note_c_img" accept="image/*" multiple="multiple" class="form-control-file" id="exampleFormControlFile1">
            <img src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCVO.note_c_id}" style="width:300px; height:200px">
        </div>
          <div class="bd pt-5">
          <button type="submit" class="btn btn-primary1" id="btn_submit">送出</button>
          </div>
        
        </div>

        

      </div>

    </div>
  <!-- /.container -->
  <br>

<input type="hidden" name="action" value="insert">

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
  
  
  
  
	$(document).on('change','input.form-control-file',function(e) {

		var input_el = $(this);
	    var img_el = input_el.parent().children().next('img');

		var file = e.target.files[0];
		var reader = new FileReader(); // 用來讀取檔案的物件

	// 點選取消，也是有觸發change事件，但file是undefined
		if (typeof (file) != 'undefined') {
		   reader.readAsDataURL(file); // 讀取檔案
		}

	// 檔案讀取完成
		reader.addEventListener("load",function() {
			let img_el_change = '<img src="'+ reader.result + '" style="width:300px; height:200px">';
			img_el.replaceWith(img_el_change);

		})

		})



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