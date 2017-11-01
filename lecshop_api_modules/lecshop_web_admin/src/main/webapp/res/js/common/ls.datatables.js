var dateTableConfig = function () {
  var _config = {
    pageLength: 10
  };
  var set = function (key, value) {
    _config[key] = value;
  };
  var get = function () {
    return _config;
  };
  return {
    set: set,
    get: get
  }
}();

$.fn.lsDataTable = function (init) {
  var _init = {
    "language": {
      "sProcessing": "处理中...",
      "sLengthMenu": "显示 _MENU_ 项结果",
      "sZeroRecords": "没有匹配结果",
      "sInfo": "当前第 _START_ - _END_ 条，共 _TOTAL_ 条",
      "sInfoEmpty": "没有符合条件的记录",
      "sInfoFiltered": "",
      "sInfoPostFix": "",
      "sSearch": "搜索:",
      "sUrl": "",
      "sEmptyTable": "暂未检索到相关数据",
      "sLoadingRecords": "载入中...",
      "sInfoThousands": ",",
      "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页"
      },
      "oAria": {
        "sSortAscending": ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
      }
    },
    "pagingType": "full_numbers",
    "searching": false,
    "processing": true,
    "serverSide": true
  };

  $.extend(true, init, _init);

  $.extend(true, init, dateTableConfig.get());

  $(this).dataTable(init);

};


var pageDataTable = function (data) {
  var start = data.start;
  var pageSize = data.length;

  var pageNum = Math.floor(start / pageSize);

  var pageInfo = {
    pageNum: pageNum,
    pageSize: pageSize
  }
  return pageInfo;
};

var dataTableAjaxData = function (dataTableAjaxData, formDataArray) {
  var data = pageDataTable(dataTableAjaxData);
  if (formDataArray && formDataArray.length > 0) {
    _.map(formDataArray, function (obj) {
      data[obj.name] = obj.value;
    });
  }
  return data;
};
