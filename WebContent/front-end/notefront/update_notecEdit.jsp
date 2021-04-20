<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.notec.model.*"%>
<%@ page import="com.note.model.*"%>
<%@page import="java.util.*"%>


<%
  NoteCVO noteCVO = (NoteCVO) request.getAttribute("noteCVO");

// NoteCService noteCSvc = new NoteCService();
// List<NoteCVO> list = noteCSvc.getAllNoteC(noteCVO.getNote_id());
// pageContext.setAttribute("list", list);
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
</style>
<body>
<!--     <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar" style="background: #85867F;color: var(--red); height: 60px;"> -->
<!--         <div class="container"><a class="navbar-brand logo" href="#">font-end</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button> -->
<!--             <div class="collapse navbar-collapse" id="navcol-1">  -->
<!--                 <ul class="navbar-nav ml-auto"> -->
<!--                     <li class="nav-item" style="height: 11.6667px;"> -->
<!--                         <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -71px;width: 0px;height: -8px;margin: -5px;color: rgba(0,0,0,0.5);">place&nbsp;</a> -->
<!--                             <div class="dropdown-menu" style="margin: -5px 0px 0px;padding: 3px 0px;height: 99px;width: 144px;"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<!--                         <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -54px;width: 0px;height: -8px;margin: -1px;color: rgba(0,0,0,0.5);">trip&nbsp;</a> -->
<!--                             <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<!--                         <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -64px;width: 0px;height: -8px;margin: -3px;color: rgba(0,0,0,0.5);">note</a> -->
<!--                             <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li class="nav-item"> -->
<!--                         <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -64px;width: 0px;height: -8px;margin: -3px;color: rgba(0,0,0,0.5);">product</a> -->
<!--                             <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                     <li class="nav-item" style="height: 38px;"> -->
<!--                         <div class="nav-item dropdown" style="padding: 4px;width: 82.7083px;margin: 0px;height: 30px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: 20px;width: 0px;height: -8px;margin: -3px;color: rgba(0,0,0,0.5);">user</a> -->
<!--                             <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </div> -->
<!--         </div> -->
<!--     </nav> -->
    <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font class="error pt-5"style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/notec/notec.do" name="form1" enctype="multipart/form-data">
  <div class="container">

    <div class="row mt-5 mb-5 ">
  <!-- Title -->
  <div class="col-lg-8">
    <h1 class="edit">編輯內容</h1>
  </div>
      <!-- Post Content Column -->
      <div class="col-lg-8 mt-3">

		<tr>
				<td>選擇主標題 :</td>
				<td>
				<jsp:useBean id="noteSvc" scope="page"
						class="com.note.model.NoteService" />


<!-- 					<FORM METHOD="post" ACTION="notec.do"> -->
						<select class="form-control" size="1" name="note_id">
<%-- 							<c:forEach var="noteCVO" items="${noteCSvc.all}"> --%>
								<option value="${noteCVO.note_id}">${noteSvc.getOneNote(noteCVO.note_id).note_title}
<%-- 							</c:forEach> --%>
						</select>
<!-- 					</FORM> -->
					</td>
			</tr>
			
          <div class="form-group pt-3">
            <label for="exampleFormControlInput1">副標題</label>
            <input type="text" name="note_c_title" class="form-control" id="exampleFormControlInput3" value="${noteCVO.note_c_title}"><span id="title3" style="color:red"></span>
          </div>
          <div class="form-group">
            <label for="exampleFormControlTextarea1">內容簡介</label>
            <textarea type="text" name="note_c_content" class="form-control" id="Input2" rows="10">${noteCVO.note_c_content}</textarea>
          </div>
          <script type="text/javascript">
		  	$(document).ready(function() {
		  	  CKEDITOR.replace( 'Input2' );
              var $CKcontent = CKEDITOR.instances.Input2.getData();
		 	 })
 		</script>
        
      

          <div class="form-group pt-3">
            <label for="exampleFormControlFile1">編輯相片</label>
            <input type="file" name="note_c_img" accept="image/*" multiple="multiple" class="form-control-file" id="exampleFormControlFile1">
            <img src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCVO.note_c_id}" style="width:300px; height:200px">
        </div>
          <div class="bd pt-5">
          <button type="submit" class="btn btn-primary1" id="btn_submit">送出</button>
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/notec/notec.do" style="margin-bottom: 0px;">
			<input type="hidden" name="note_c_id"  value="${noteCVO.note_c_id}">
			<input type="hidden" name="action" value="delete">
          	<button type="submit" class="btn btn-primary1" id="btn_submit" name="note_classid" value="0">刪除</button>
          	</FORM>
          </div>
        
        </div>

        

      </div>

    </div>
  <!-- /.container -->
  <br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="note_c_id" value="${noteCVO.note_c_id}">

  </FORM>
  <script>
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

        $("#btn_submit").on("click", function (e) {
            let task_text = ($('#exampleFormControlInput3').val()).trim();
            if (task_text == "") {
                e.preventDefault();
                $('#title3').text('*請勿空白');
            } else {
                $('#title3').text('')
            }
        });

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