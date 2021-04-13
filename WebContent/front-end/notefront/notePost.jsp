<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.notec.model.*"%>
<%@ page import="com.note.model.*"%>

<%

// 	NoteService noteSvc = new NoteService();
//     NoteVO getOneNote = noteSvc.getOneNote(note_id)
// 	pageContext.setAttribute("getOneNote", getOneNote);

// 	NoteCService noteCSvc = new NoteCService();
// 	List<NoteCVO> list = noteCSvc.getAll();
// 	pageContext.setAttribute("list", list);
	
	NoteVO noteVO = (NoteVO) request.getAttribute("noteVO");
	
	NoteCService noteCSvc = new NoteCService();
	List<NoteCVO> Clist = noteCSvc.getAllNoteC(noteVO.getNote_id());
	pageContext.setAttribute("Clist", Clist);

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
</head>

<body>
    <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar" style="background: #85867F;color: var(--red);">
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
    <main class="page blog-post pd-3">
        <section class="clean-block clean-post dark">
            <div class="container">
                <div class="block-content">
                    <div class="post-image" style="background-image:url(&quot;<%=request.getContextPath()%>/front-end/notefront/assets/img/scenery/image3.jpg&quot;);"></div>
                    <div class="post-body">
                        <h3>${noteVO.note_title}</h3>
                        <div class="post-info"><span>By ${noteVO.users_id}</span><span><fmt:formatDate
  												pattern="yyyy-MM-dd" value="${noteVO.note_date}" /></span></div>
                        <p>${noteVO.note_description}</p>


                        <c:forEach var="noteCVO" items="${Clist}" >
                        <div class="row">
                            <div class="col-md-6">
                                <figure class="figure"><img class="rounded img-fluid figure-img" src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCVO.note_c_id}"
										style="width: 300px; height: 200px;" alt="A generic square placeholder image with rounded corners in a figure.">
                                    <figcaption class="figure-caption"></figcaption>
                                </figure>
                            </div>
                            <div class="col">
                            	<h>${noteCVO.note_c_title}</h>
                                <p>${noteCVO.note_c_content}</p>
                            </div>
<!--                         </div> -->
<!--                         <h4>Lorem Ipsum dolor</h4> -->
<!--                         <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p> -->
<!--                         <div class="row"> -->
<!--                             <div class="col"> -->
<!--                                 <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p> -->
<!--                             </div> -->
<!--                             <div class="col-md-6"> -->
<!--                                 <figure class="figure"><img class="rounded img-fluid figure-img" src="assets/img/scenery/image5.jpg" alt="A generic square placeholder image with rounded corners in a figure."> -->
<!--                                     <figcaption class="figure-caption">Lorem Ipsum dolor</figcaption> -->
<!--                                 </figure> -->
<!--                             </div> -->
<!--                         </div> -->
                    </div>
                    </c:forEach>
                </div>
            </div>
        </section>
    </main>
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