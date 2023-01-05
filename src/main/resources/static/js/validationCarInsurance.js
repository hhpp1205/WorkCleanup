function submitForm() {
    const form = document.getElementById("car_insurance_form");
    const carSelect = document.getElementById("carId");
    const insuranceSelect = document.getElementById("insuranceId");

    if (carSelect.value == '' && insuranceSelect.value != '') {
        alert("차량을 선택해 주세요");
    } else if(carSelect.value != '' && insuranceSelect.value == ''){
        alert("보험을 선택해 주세요");
    } else if (carSelect.value == '' && insuranceSelect.value == '') {
        alert("차량을 선택해 주세요");
    } else {
        form.submit();
    }
}
