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
 <%@ include file="/front-end/pages/headNav.html" %>
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