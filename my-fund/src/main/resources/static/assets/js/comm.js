//[Preview Menu Javascript]

//Project:	AIUI Admin - Responsive Admin Template
//Primary use:   This file is for demo purposes only.

$(function () {
  'use strict'

  /**
   * List of all the available skins
   *
   * @type Array
   */
  var mySkins = [
    'skin-blue',
    'skin-black',
    'skin-red',
    'skin-yellow',
    'skin-purple',
    'skin-green',
  ]

  /**
   * , 를 찍는 함수를 실행한다.
   *
   * @param 10000 같은 String Value
   * @returns ,를 찍은 값을 return 한다.
   */
  function setMoneyAmt(str) {
	  str = String(str);

	  return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
  }

});// End of use strict
