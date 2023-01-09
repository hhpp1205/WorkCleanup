function submitForm() {
    const form = document.getElementById("excel_form");
    const startDate = document.getElementById("startDate");
    const endDate = document.getElementById("endDate");
    const insuranceName = document.getElementById("insuranceName");
    const excelType = document.getElementById("excelType");

    if(startDate.value == null || startDate.value == ''){
        alert("시작 날짜를 입력하세요");
        startDate.focus();
        return;
    }else if (endDate.value == null || endDate.value == ''){
        alert("끝 날짜를 입력하세요");
        endDate.focus();
        return;
    }else if ((insuranceName.value == null || insuranceName.value == '') && excelType.value == 'CAR'){
        form.submit();
    } else if(insuranceName.value == null || insuranceName.value == ''){
        alert("보험종류를 선택하세요");
        insuranceName.focus();
        return;
    }else if (excelType.value == null || excelType.value == '') {
        alert("엑셀종류를 선택하세요");
        excelType.focus();
        return;
    } else {
        form.submit();
    }
}