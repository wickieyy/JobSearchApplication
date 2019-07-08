function checkCookieForEmployee(){
    var x=document.cookie.split(";");
    for(var i=0;i<x.length;i++){
        if(x[i].includes("employeeEmail")){
            x=x[i];
            break;
        }
    }
	var x=x.split("=").pop();
	var url=window.location.href;
	var currentPage=url.split("/").pop();
	if(x==""){
		if(currentPage=="jobseeker.jsp" || currentPage=="EmployeeLogin.action"){
			window.location.href="EmployeeLogin.jsp"
		}
		if(currentPage=="EmployeeLogin.jsp"){
		}
	}
	else{
		if(currentPage=="EmployeeLogin.jsp"){
			window.location.href="jobseeker.jsp"
		}
		if(currentPage=="jobseeker.jsp" || currentPage=="EmployeeLogin.action"){
			var uname=x;
			uname=uname.split("@");
			document.getElementById("employeeUser").innerHTML=uname[0].split("=").pop();
		}
	}
}
function checkCookie(){
    var x=document.cookie.split(";");
    for(var i=0;i<x.length;i++){
        console.log(x[i]);
        if(x[i].includes("employerEmail")){
            x=x[i];
            break;
        }
    }
    var x=x.split("=").pop();
    console.log(x);
	var url=window.location.href;
	var currentPage=url.split("/").pop();
	if(x==""){
		if(currentPage=="employerHome.jsp"){
			window.location.href="EmployerLogin.jsp"
		}
		if(currentPage=="Login.jsp"){
		}
	}
	else{
		if(currentPage=="EmployerLogin.jsp"){
			window.location.href="employerHome.jsp"
		}
		if(currentPage=="employerHome.jsp" ||  currentPage=="EmployerLogin.action"){
			var uname=x;
			uname=uname.split("@");
			document.getElementById("employerUser").innerHTML=uname[0].split("=").pop();
		}
	}
}
$(document).ready(function(){
    $("#postJobsForm").hide();
    $("#applyJobsForm").hide();
    $("#postNewJobsButton").click(function(){
      $("#postJobsForm").show();
      $("#employerHomeBody").css("opacity","0.5");
      $("#appliedForJobs").css("opacity","0.5");
      $("#employerProfile").css("opacity","0.5");
    });
  });
  $(document).ready(function(){
    $("#closeForm").click(function(){
      $("#postJobsForm").hide();
      document.getElementById("postNewJobForm").reset();
      $("#employerHomeBody").css("opacity","1");
      $("#appliedForJobs").css("opacity","1");
      $("#employerProfile").css("opacity","1");
    });
    $(document).on('keydown', function(event) {
        if (event.key == "Escape") {
             $("#postJobsForm").hide();
             $("#applyJobsForm").hide();
             $("#employerHomeBody").css("opacity","1");
             $("#appliedForJobs").css("opacity","1");
             $("#employerProfile").css("opacity","1");
        }
    });
  }); 
  $(document).ready(function(){
        $(document).on('keydown', function(event) {
            if (event.key == "Escape") {
                $("#actionMessageEmployeeHome").hide();
                $("#actionMessageEmployerHome").hide();
            }
            if (event.key == "Enter") {
                $("#actionMessageEmployeeHome").hide();
                $("#actionMessageEmployerHome").hide();
            }
        });
  });
  var PostedJobJson;
  var AvailableJobJson;
  var AppliedJobCount;
function getApplicationCount(){

}
function loadPostedJobs(){
    checkCookie();
    getApplicationCount();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            PostedJobJson=JSON.parse(xhttp.responseText);
            var jsonSize=PostedJobJson["size"];
            if(jsonSize==0) $("#postedJobsCards").prepend("<h5 id='postedJobsHead'>No Jobs Posted</h5>");
            else
            { 
                document.getElementById("comapanyName").value=PostedJobJson["companyName"+0];
                var xhttp1=new XMLHttpRequest();
                xhttp1.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        AppliedJobCount=JSON.parse(xhttp1.responseText);
                        for(var i=0;i<jsonSize;i++){
                            var postedJobsCard='<div class="card">'+
                                                    '<div class="card-body">'+
                                                        '<h4 class="card-title"><span class="postedPageCardTitle"></span><span><small class="postedPageCardTitleLocation"></small></span><span><button class="btn btn-success btn-sm" onclick="togglePostedJobStatus('+i+')"><span class="statusButton"></span>&nbsp;&#9660;</button></span></h4>'+
                                                        '<div class="postedJobsCardBody">'+
                                                            '<span class="postedPageCardPost">&emsp;Vacancies in Post : </span>'+
                                                            '&emsp;&emsp;<span class="postedPageDepartment">Salary : </span>'+
                                                            '&emsp;&emsp;&emsp;<span class="postedPageRequiredExperience">Expected Experience : </span><br><br>'+
                                                            '<span class="postedPageRequiredSkills">&emsp;Expected Skills : </span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="numberOfApplications">Number of Applied candidates : </span><br>'+ //
                                                            '<br><small><span class="postedPagePostedBy">posted by : </span></small>'+
                                                        '</div>'+
                                                    '</div> '+
                                                '</div><br>';            
                            $("#postedJobsCards").append(postedJobsCard);
                            $(".postedPageCardTitle")[i].append(PostedJobJson["companyName"+i]+", ");
                            $(".postedPageCardTitleLocation")[i].append(PostedJobJson["location"+i]);
                            $(".statusButton")[i].append(PostedJobJson["vacancyStatus"+i]);
                            $(".postedPageCardPost")[i].append(PostedJobJson["post"+i]);
                            $(".postedPageDepartment")[i].append(PostedJobJson["salary"+i]);
                            $(".postedPageRequiredExperience")[i].append(PostedJobJson["requiredExperience"+i]+" year(s)");
                            $(".postedPageRequiredSkills")[i].append(PostedJobJson["skills"+i]);
                            $(".postedPagePostedBy")[i].append(PostedJobJson["postedBy"+i]);
                            // console.log("ZZ"+AppliedJobCount[""+PostedJobJson["postId"+i]]);
                            var applicationsCountNumber = AppliedJobCount[""+PostedJobJson["postId"+i]];
                            if(!applicationsCountNumber>0){
                                applicationsCountNumber=0;
                            }
                            $(".numberOfApplications")[i].append(applicationsCountNumber);
                            var statusButton = document.getElementsByClassName("statusButton");
                            if(statusButton[i].innerHTML=="Open") statusButton[i].parentNode.setAttribute("class","btn btn-success btn-sm");
                            if(statusButton[i].innerHTML=="Closed")  statusButton[i].parentNode.setAttribute("class","btn btn-danger btn-sm");
                        }
                        $("#postedJobsCards").prepend("<h3 id='postedJobsHead'>Posted Jobs</h3>");
                        $("#employerProfileEmail").val(PostedJobJson["employerProfileEmail"]);
                        $("#employerProfileFullName").val(PostedJobJson["employerFullName"]);
                        $("#employerProfileCompanyName").val(PostedJobJson["employerCompanyName"]);
                        $("#ProfileCurrentMobileNumber").val(PostedJobJson["employerMobileNumber"]);
                        $("#ProfileCurrentLocation").val(PostedJobJson["employerLocation"]);
                    }
                }
                xhttp1.open("GET","PostedJobsGetAppliedCount",true);
                xhttp1.send();  
            }
        }
    }
    xhttp.open("GET","PostedJobs",true);
    xhttp.send();
    document.getElementById("employerHomePageNav").style="color:#818181";
}
function loadPostedJobsForSeekers(){
    checkCookieForEmployee();
    $("#employerHomeNav").css("color","#f1f1f1");
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            AvailableJobJson=JSON.parse(xhttp.responseText);
            var jsonSize=AvailableJobJson["size"];
            if(jsonSize==0) $("#postedJobsCards").prepend("<h5 id='postedJobsHead'>No Jobs Currently Available</h5>");
            else
            { 
                var selectTag = document.getElementById("filterSelectTag");
                var map = new Map();
                for(var i=0;i<jsonSize;i++){
                    if(!map.has(AvailableJobJson["department"+i])){
                        map.set(AvailableJobJson["department"+i],i);
                        var option = document.createElement("option");
                        option.text = AvailableJobJson["department"+i];
                        selectTag.add(option);
                    }
                    console.log(map);
                }
                var selectTag = document.getElementById("filterSelectTag1");
                var map1 = new Map();
                for(var i=0;i<jsonSize;i++){
                    if(!map1.has(AvailableJobJson["salary"+i])){
                        map1.set(AvailableJobJson["salary"+i],i);
                        var option = document.createElement("option");
                        option.text = "Rs."+AvailableJobJson["salary"+i];
                        selectTag.add(option);
                    }
                }
                filterFunction();
            }
        }
    }
    xhttp.open("GET","PostedJobsForSeekers?",true);
    xhttp.send();
}
function togglePostedJobStatus(i){
    var status=document.getElementsByClassName("statusButton")[i].innerHTML;
    if(status=="Closed"){
        status="Open";
    }
    else if(status=="Open"){
        status="Closed";
    }
    var bool=confirm("Change Status to "+status+" ?");
    var postid=PostedJobJson["postId"+i];
    if(bool==true){
        var xhttp=new XMLHttpRequest();
        xhttp.onreadystatechange = function(){
            if(this.readyState == 4 && this.status == 200){
                if(status=="Closed"){
                    var statusButton = document.getElementsByClassName("statusButton");
                    statusButton[i].parentNode.setAttribute("class","btn btn-danger btn-sm");
                    statusButton[i].innerHTML=status;
                }
                if(status=="Open"){
                    var statusButton = document.getElementsByClassName("statusButton");
                    statusButton[i].parentNode.setAttribute("class","btn btn-success btn-sm");
                    statusButton[i].innerHTML=status;
                }
            }
        }
        xhttp.open("GET","TogglePostedJobStatus?postid="+postid+"&status="+status,true);
        xhttp.send();
    }
}
function applyForJob(i,status){
    $("#applyJobsForm").show();
    $("#currentMobileNumber").val(AvailableJobJson["userMobileNumber"]);
    $("#experience").val(AvailableJobJson["userExperienceYears"]);
    $("#currentLocation").val(AvailableJobJson["userLocation"]);
    $("#employeeHomeBody").css("opacity","0.5");
    $("#applyPostId").val(i);
    $("#applyCloseForm").click(function(){
        document.getElementById("applyNewJobForm").reset();
        $("#applyJobsForm").hide();
        $("#employeeHomeBody").css("opacity","1");
      });
      $(document).on('keydown', function(event) {
        if (event.key == "Escape") {
            $("#applyJobsForm").hide();
            $("#employeeHomeBody").css("opacity","1");
        }
    });
}
function viewApps(){
    $("#postJobsForm").hide();
    $("#employerHomeBody").css("opacity","1");
    $("#appliedForJobs").css("opacity","1");
    $("#employerProfile").css("opacity","1");
    document.getElementById("employerProfile").style="display:none";
    $("#employerHomePageNav").css("color","#818181");
    $("#employerProfileButton").css("color","#818181");
    $("#viewApps").css("color","#f1f1f1");
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            $("#postedJobsCards").hide();
            $("#appliedForJobs").show();
            applications=JSON.parse(xhttp.responseText);
            console.log(applications["size"]);
            document.getElementById("appliedForJobs").innerHTML="";
            for(var i=0 ;i<applications["size"];i++){
                document.getElementById("appliedForJobs").innerHTML+='<div class="card">'+
                                '<div class="card-body">'+
                                    '<h4 class="card-title"><span class="AppliedPageCardTitle"></span><span><small class="postedPageCardTitleLocation"></small></span><span class="badge badge-success statusFlag"></span></h4>'+
                                    '<div class="postedJobsCardBody">'+
                                        '<span class="AppliedPageCardDepartment">&emsp;Designation : </span>'+
                                        '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="AppliedPageSalary">Salary : </span>'+
                                        '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="ApplicantExperience">Applicant Experience : </span><br><br>'+
                                        '<span class="AppliedPageName">&emsp;Applicant Name : </span>'+
                                        '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="applicantLocation">Applicant Location : </span><br><br>'+
                                        '<span class="AppliedPageSkills">&emsp;Applicant Skills : </span>'+ //<span class="numberOfApplications">Number of Applied candidates : </span>
                                        '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="applicantEmail">Applicant Email : </span><br><br>&emsp;<span class="applicantMobileNumber">Applicant Mobile Number : </span>'+
                                        '<br><small><span class="AppliedPagePostedBy">posted by : </span></small>'+
                                    '</div>'+
                                '</div> '+
                            '</div><br>';
                $(".AppliedPageCardTitle")[i].append(applications["companyName"+i]);
                $(".AppliedPageCardDepartment")[i].append(applications["post"+i]);
                $(".AppliedPageSalary")[i].append(applications["salary"+i]);
                $(".ApplicantExperience")[i].append(applications["applicantExperience"+i]);
                $(".applicantEmail")[i].append(applications["applicantEmail"+i]);
                $(".AppliedPageSkills")[i].append(applications["applicantSkills"+i]);
                $(".applicantMobileNumber")[i].append(applications["applicantMobileNumber"+i]);
                $(".AppliedPagePostedBy")[i].append(applications["postedby"+i]);
                $(".AppliedPageName")[i].append(applications["applicantName"+i]);
                $(".applicantLocation")[i].append(applications["applicantLocation"+i]);
            }
            $("#appliedForJobs").prepend("<h3 id='postedJobsHead'>Applications</h3>");
        }
    }
    xhttp.open("GET","viewApplications",true);
    xhttp.send();  
}
function showEmployerHome(){
    $("#appliedForJobs").hide();
    $("#employerHomeBody").css("opacity","1");
    $("#appliedForJobs").css("opacity","1");
    $("#employerProfile").css("opacity","1");
    $("#postedJobsCards").show();
    $("#postJobsForm").hide();
    $("#employerHomePageNav").css("color","#f1f1f1");
    $("#viewApps").css("color","#818181");
    $("#employerProfileButton").css("color","#818181");
    document.getElementById("employerProfile").style="display:none";
}
function showEmployeeProfile(){
    $("#postedJobsCards").hide();
    $("#filterJobs").hide();
    $("#userProfile").css("display","block");
    $("#employerHomeNav").css("color","#818181")
    $("#employerProfileNav").css("color","#f1f1f1");
    $("#ProfileCurrentMobileNumber").val(AvailableJobJson["userMobileNumber"]);
    $("#ProfileExperience").val(AvailableJobJson["userExperienceYears"]);
    $("#ProfileCurrentLocation").val(AvailableJobJson["userLocation"]);
    $("#profilePreferedDesignations").val(AvailableJobJson["userPreferedDesignations"]);
}
function showEmployeeHome(){
    $("#userProfile").css("display","none");
    $("#postedJobsCards").show();
    $("#filterJobs").show();
    $("#employerProfileNav").css("color","#818181");
    $("#employerHomeNav").css("color","#f1f1f1");
}
function showEmployeeLogout(){
    $("#employerProfileNav").css("color","#818181");
    $("#employerHomeNav").css("color","#818181");
    window.location.href="EmployeeLogin.jsp";
    document.cookie="employeeEmail=";
}
function showEmployerLogout(){
    window.location.href="EmployerLogin.jsp";
    document.cookie="employerEmail=";
}
function getPreferences(){
    var loc= window.location.href;
    var locArr=loc.split("/").pop();
    var newUrl=loc.replace(locArr,"GetPreferences.action");
    location.replace(newUrl);
}

function loadPreferrences(){
    var label = document.getElementsByClassName("label");
    for(var i=0;i<label.length;i++){
        if(label[i].innerHTML=="OtherPreferences:"){
            label[i].style="display:none";
        }
    }
    var pref = document.getElementsByClassName("checkboxLabel");
    for(var i=0;i<pref.length;i++){
        pref[i].setAttribute("onclick","showOtherPreferences("+i+");");
        pref[i].checked=false;
    }
    var pref = document.getElementsByName("colors");
    for(var i=0;i<pref.length;i++){
        pref[i].setAttribute("onclick","showOtherPreferences("+i+");");
        pref[i].checked=false;
    }
}
function showOtherPreferences(j){
    var pref=document.getElementsByName("colors");
    var prefLabel = document.getElementsByClassName("checkboxLabel");
    if(pref[j].checked==true){
        if(prefLabel[j].innerHTML=="Other"){
            document.getElementById("OtherPreferences").style="display:block";
            var label = document.getElementsByClassName("label");
            for(var i=0;i<label.length;i++){
                if(label[i].innerHTML=="OtherPreferences:"){
                    label[i].style="display:block";
                }
            }
        }
    }
    else{
        if(prefLabel[j].innerHTML=="Other"){
            document.getElementById("OtherPreferences").style="display:none";
            var label = document.getElementsByClassName("label");
            for(var i=0;i<label.length;i++){
                if(label[i].innerHTML=="OtherPreferences:"){
                    label[i].style="display:none";
                }
            }
        }
    }
}
$(document).ready(function(){
    $("#profileApplyNewJobForm :input").change(function() {
        document.getElementById("profileApplyNewJobForm").setAttribute("changed","true");
    });
});
function enableEditing(){
    document.getElementById("ProfileCurrentMobileNumber").removeAttribute("readonly");
    document.getElementById("ProfileCurrentLocation").removeAttribute("readonly");
    document.getElementById("ProfileExperience").removeAttribute("readonly");
    document.getElementById("profilePreferedDesignations").removeAttribute("readonly");
    document.getElementById("submitButton").removeAttribute("disabled");
    $("#userProfile input").css("background-color","white");
    $("#userProfile textarea").css("background-color","white");
    document.getElementById("profileApplicantEmail").style="background-color:#f1f1f1";
}
function getApplicationCountForSeekers(){

}
function filterFunction(){
    var xhttp=new XMLHttpRequest();
    var AppliedJobCountForSeekers;
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            AppliedJobCountForSeekers=JSON.parse(xhttp.responseText);
            var selectedArray = new Array();
            var selObj = document.getElementById('filterSelectTag1'); 
            var i;
            var count = 0;
            for (i=0; i<selObj.options.length; i++) { 
                if (selObj.options[i].selected) {
                    selectedArray[count] = selObj.options[i].value;
                    count++; 
                } 
            } 
            var SelectedSalarayString="";
            for(var k=0;k<selectedArray.length;k++){
                SelectedSalarayString+=selectedArray[k]+",";
            }
        
            var selectedArrayDesignations = new Array();
            var selObj = document.getElementById('filterSelectTag'); 
            var i;
            var count = 0;
            for (i=0; i<selObj.options.length; i++) { 
                if (selObj.options[i].selected) {
                    selectedArrayDesignations[count] = selObj.options[i].value;
                    count++; 
                } 
            }
            var SelectedDesignationString="";
            for(var k=0;k<selectedArrayDesignations.length;k++){
                SelectedDesignationString+=selectedArrayDesignations[k]+",";
            }
            var jsonSize=AvailableJobJson["size"];
            var selectTagByDesginations  = document.getElementById("filterSelectTag");
            var selectTagBySalary = document.getElementById("filterSelectTag1");
            document.getElementById("postedJobsCards").innerHTML="";
            var cardCount=0;
            for(var i=0;i<jsonSize;i++){
                if(!SelectedDesignationString.includes(AvailableJobJson["department"+i]) && selectTagByDesginations.value!="none"){
                    continue;
                }
                if(!SelectedSalarayString.includes("Rs."+AvailableJobJson["salary"+i]) && selectTagBySalary.value!="none"){
                    continue;
                }
                var applicationsCount = 0;
                // console.log(AvailableJobJson["postId"+i]+"XX");
                var postedJobsCard='<div class="card">'+
                                        '<div class="card-body">'+
                                            '<h4 class="card-title"><span class="postedPageCardTitle"></span><span><small class="postedPageCardTitleLocation"></small></span><span class="badge badge-success statusFlag"></span></h4>'+
                                            '<div class="postedJobsCardBody"><br>'+
                                                '<span class="postedPageCardDepartment">&emsp;Designation : </span>'+
                                                '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="postedPageSalary">Salary : </span>'+
                                                '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="postedPageRequiredExperience">Expected Experience : </span><br><br>'+
                                                '<span class="postedPageRequiredSkills">&emsp;Expected Skills : </span> &emsp;&emsp; &emsp;&emsp;<span class="numberOfApplications">Number of Applied candidates : </span><br>'+ 
                                                '<span><button class="btn btn-primary btn-sm applyButton"   onclick="applyForJob('+AvailableJobJson["postId"+i]+')">Apply</button></span><br>'+
                                                '<small><span class="postedPagePostedBy">posted by : </span></small>'+
                                            '</div>'+
                                        '</div> '+
                                    '</div><br>';                 
                $("#postedJobsCards").append(postedJobsCard);
                $(".postedPageCardTitle")[cardCount].append(AvailableJobJson["companyName"+i]+", ");
                $(".postedPageCardTitleLocation")[cardCount].append(AvailableJobJson["location"+i]);
                $(".statusFlag")[cardCount].append(AvailableJobJson["vacancyStatus"+i]);
                $(".postedPageCardDepartment")[cardCount].append(AvailableJobJson["department"+i]);
                $(".postedPageSalary")[cardCount].append(AvailableJobJson["salary"+i]);
                $(".postedPageRequiredExperience")[cardCount].append(AvailableJobJson["requiredExperience"+i]+" year(s)");
                $(".postedPageRequiredSkills")[cardCount].append(AvailableJobJson["skills"+i]);
                $(".postedPagePostedBy")[cardCount].append(AvailableJobJson["postedBy"+i]);
                var appCount = AppliedJobCountForSeekers[AvailableJobJson["postId"+i]];
                if(!appCount>0){
                    appCount=0;
                }
                $(".numberOfApplications")[cardCount].append(appCount);
                var alreadyApplied=AppliedJobCountForSeekers["alreadyApplied"];
                if(alreadyApplied["alreadyApplied"+i]==AvailableJobJson["postId"+i]){
                    var applyButton = document.getElementsByClassName("applyButton");
                    applyButton[cardCount].innerHTML="Already Applied";
                    applyButton[cardCount].setAttribute("disabled","true");
                    applyButton[cardCount].style="width:120px;"
                }
                var statusFlag = document.getElementsByClassName("statusFlag");
                if(statusFlag[cardCount].innerHTML=="Closed"){  
                    applyButton[cardCount].hidden = "true";
                    statusFlag[cardCount].setAttribute("class","badge badge-danger statusFlag");
                }
                cardCount++;
            } 
            var none=document.getElementById("postedJobsCards");
            if(none.innerHTML==""){
                none.innerHTML+="<br><h4 style='text-align:center;'>No Jobs Available for applied Filter</h4>";
            }
            $("#postedJobsCards").prepend("<h3 id='availableJobsHead'>Available Opportunities</h3>");
        }
    }
    xhttp.open("GET","GetAppliedCountForSeekers",true);
    xhttp.send();  
}
$(document).ready(function(){
    $("#selectTagSalary").click(function(){
      $("#designationsSelect").slideUp("fast");
      $("#salarySelect").slideToggle("fast");
    });
  });
  $(document).ready(function(){
    $("#selectTagDesignations").click(function(){
      $("#salarySelect").slideUp("fast");
      $("#designationsSelect").slideToggle("fast");
    });
  });
  $(document).ready(function(){
    $("#filterSelectTag1").blur(function(){
        $("#salarySelect").slideUp("fast");
    });
  });
  $(document).ready(function(){
    $("#filterSelectTag").blur(function(){
        $("#designationsSelect").slideUp("fast");
    });
  });
  function showEmployerProfile(){
      $("#postJobsForm").hide();
      $("#employerHomeBody").css("opacity","1");
      $("#appliedForJobs").css("opacity","1");
      $("#employerProfile").css("opacity","1");
      $("#employerHomePageNav").css("color","#818181");
      $("#employerProfileButton").css("color","#f1f1f1");
      $("#viewApps").css("color","#818181");
      document.getElementById("employerProfile").style="display:block";
      document.getElementById("postedJobsCards").style="display:none";
      $("#appliedForJobs").hide();
  }
  function enableEditingForEmployerProfile(){
      document.getElementById("employerProfileFullName").removeAttribute("readonly");
      document.getElementById("employerProfileCompanyName").removeAttribute("readonly");
      document.getElementById("ProfileCurrentMobileNumber").removeAttribute("readonly");
      document.getElementById("ProfileCurrentLocation").removeAttribute("readonly");
      document.getElementById("EmployerProfileSubmitButton").removeAttribute("disabled");
      $("#employerProfile input").css("background-color","white");
      document.getElementById("employerProfileEmail").style="background-color:#f1f1f1";  
  }