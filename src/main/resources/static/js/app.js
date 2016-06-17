var app = angular.module('fileApp', ['angular-encryption']);

app.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);

app.service('fileUpload', [ '$http', function($http) {
	this.uploadFileToUrl = function(file, hash, uploadUrl) {
		var fd = new FormData();
		fd.append('file', file);
		fd.append('name', file.name)
		fd.append('sha256', hash)
		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function() {
		}).error(function() {
		});
	}
} ]);

app.controller('file-controller', [ '$scope', 'fileUpload', 'sha256', function($scope, fileUpload, sha256) {
	$scope.uploadFile = function() {
		var file = $scope.myFile;
		var uploadUrl = "/upload";
		var hash = sha256.convertToSHA256(file);
		console.log("file sha256-hash=" + hash);
		fileUpload.uploadFileToUrl(file, hash, uploadUrl);
	};

} ]);