<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Blog Post - font-end</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="assets/css/smoothproducts.css">
    <link href="assets/css/blog-home.css" rel="stylesheet">
    <script src="assets/js/jquery.min.js"></script>
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
    <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar" style="background: #85867F;color: var(--red); height: 60px;">
        <div class="container"><a class="navbar-brand logo" href="#">font-end</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1"> 
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item" style="height: 11.6667px;">
                        <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -71px;width: 0px;height: -8px;margin: -5px;color: rgba(0,0,0,0.5);">place&nbsp;</a>
                            <div class="dropdown-menu" style="margin: -5px 0px 0px;padding: 3px 0px;height: 99px;width: 144px;"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -54px;width: 0px;height: -8px;margin: -1px;color: rgba(0,0,0,0.5);">trip&nbsp;</a>
                            <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -64px;width: 0px;height: -8px;margin: -3px;color: rgba(0,0,0,0.5);">note</a>
                            <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="nav-item dropdown" style="padding: 5px;width: 70.7083px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: -64px;width: 0px;height: -8px;margin: -3px;color: rgba(0,0,0,0.5);">product</a>
                            <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div>
                        </div>
                    </li>
                    <li class="nav-item" style="height: 38px;">
                        <div class="nav-item dropdown" style="padding: 4px;width: 82.7083px;margin: 0px;height: 30px;"><a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: 20px;width: 0px;height: -8px;margin: -3px;color: rgba(0,0,0,0.5);">user</a>
                            <div class="dropdown-menu"><a class="dropdown-item" href="#">First Item</a><a class="dropdown-item" href="#">Second Item</a><a class="dropdown-item" href="#">Third Item</a></div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Page Content -->
  <div class="container">

    <div class="row mt-5 mb-5 ">
  <!-- Title -->
  <div class="col-lg-8">
    <h1 class="edit">編輯遊記</h1>
  </div>
      <!-- Post Content Column -->
      <div class="col-lg-8 mt-3">
        <form>
          <div class="form-group">
            <label for="exampleFormControlSelect1">行程</label>
            <select class="form-control" id="exampleFormControlSelect1">
              <option>0</option>
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
            </select>
          </div>
          <div class="form-group">
            <label for="exampleFormControlInput1">標題</label>
            <input type="title" class="form-control" id="exampleFormControlInput1"><span id="title1" style="color:red"></span>
          </div>

          <div class="form-group">
            <label for="exampleFormControlInputdate1">旅遊日期</label>
<!--             <input type="date" class="form-control" id="exampleFormControlInputdate1"> -->
            <input name="traveldate" class="form-control" id="f_date1" type="text">
          </div>
          
          
          <div class="form-group">
            <label for="exampleFormControlTextarea1">內容簡介</label>
            <textarea name="editor1" id="editor1" rows="10" cols="80"></textarea>
          </div>
            <script>
                CKEDITOR.replace( 'editor1' );
            </script>
        
      

          <div class="form-group pt-3">
            <label for="exampleFormControlFile1">新增相片</label>
            <input type="file" accept="image/*" multiple="multiple" class="form-control-file" id="p_file">
            <div id="preview"><span class="text"></span></div>
        </div>

          <div class="bd pt-5">
          <button type="submit" class="btn btn-primary1" id="btn_submit">送出</button>
          </div>
        </form>
        </div>

        

      </div>

    </div>
    <!-- /.row -->
  </div>
  <!-- /.container -->
  <script>
       var the_file_element = document.getElementById("p_file");
                the_file_element.addEventListener("change", function (e) {

                    var picture_list = document.getElementsByClassName("form-control-file")[0];
                    picture_list.innerHTML = ""; //清空
                    // 跑每個使用者的檔案
                    for (var i = 0; i < this.files.length; i++) {
                        let reader = new FileReader(); // 用來讀取檔案的物件
                        reader.readAsDataURL(this.files[i]); // 讀取檔案
                        // 檔案讀取完畢時觸發
                        reader.addEventListener("load", function () {
                            console.log(reader.result);

                            const img = document.createElement('img')
                            img.setAttribute('id', 'preview_image')
                            img.setAttribute('src', reader.result)
                            preview.appendChild(img)
                        })
                    }
                });

        $("#btn_submit").on("click", function (e) {
            let task_text = ($('#exampleFormControlInput1').val()).trim();
            if (task_text == "") {
                e.preventDefault();
                $('#title1').text('*請勿空白');
            } else {
                $('#title1').text('')
            }
        });

        
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value:   new Date()
          
        });
        // 以下為某一天之後的日期無法選擇
             var somedate2 = new Date('2021-04-08');
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
    
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="assets/js/smoothproducts.min.js"></script>
    <script src="assets/js/theme.js"></script>
</body>

</html>