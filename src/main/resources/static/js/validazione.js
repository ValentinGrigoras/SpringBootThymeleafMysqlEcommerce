$(document)
	.ready(
		function() {
			$('#userForm')
				.bootstrapValidator(
					{
						feedback: {
							valid: 'glyphicon glyphicon-ok',
							invalid: 'glyphicon glyphicon-remove',
							validating: 'glyphicon glyphicon-refresh'
						},
						fields: {
							nome: {
								container: '#infoNome',
								validators: {
									notEmpty: {
										message: 'Il campo nome non pu&ograve; essere vuoto<br>'
									},
									regexp: {
										regexp: /^[a-zA-Z ,.'-]{2,30}$/,
										message: 'Da 2 a 30 caratteri. (Solo lettere accettate)'
									}
								}
							},
							cognome: {
								container: '#infoCognome',
								validators: {
									notEmpty: {
										message: 'Il campo cognome non pu&ograve; essere vuoto<br>'
									},
									regexp: {
										regexp: /^[a-zA-Z ,.'-]{2,30}$/,
										message: 'Da 2 a 30 caratteri. (Solo lettere accettate)'
									}
								}
							},
							indirizzo: {
								container: '#infoIndirizzo',
								validators: {
									notEmpty: {
										message: 'Il campo indirizzo non pu&ograve; essere vuoto<br>'
									},
									regexp: {
										regexp: /^[a-zA-Z ,.'-]+[0-9]{1,3}$/,
										message: 'Formato indirizzo: (Via | Viale | Piazza, numero civico)'
									}
								}
							},
							cap: {
								container: '#infoCap',
								validators: {
									notEmpty: {
										message: 'Il campo cap non pu&ograve; essere vuoto<br>'
									},
									regexp: {
										regexp: /^[0-9]{5}$/,
										message: 'Inserire 5 cifre per il cap'
									}
								}
							},
							nascita: {
								container: '#infoNascita',
								validators: {
									notEmpty: {
										message: 'Il campo nascita non pu&ograve; essere vuoto<br>'
									},
									date: {
										format: 'YYYY/MM/DD',
										message: 'Data non valida. Formato previsto: YYYY/MM/DD'
									}
								}
							},
							username: {
								container: '#infoUsername',
								validators: {
									notEmpty: {
										message: 'Il campo username non pu&ograve; essere vuoto<br>'
									},
									regexp: {
										regexp: /^[a-zA-Z0-9]{4,10}$/,
										message: 'Da 4 a 10 caratteri. (Lettere e numeri accettati)'
									}
								}
							},
							password: {
								container: '#infoPassword',
								validators: {
									notEmpty: {
										message: 'Il campo password non pu&ograve; essere vuoto<br>'
									},
									regexp: {
										regexp: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{4,20}$/,
										message: 'Da 4 a 10 caratteri. (Lettere e numeri accettati, almeno una Maiuscola, una minuscola e un numero)'
									}
								}
							},
							email: {
								container: '#infoEmail',
								validators: {
									notEmpty: {
										message: 'Il campo email non pu&ograve; essere vuoto<br>'
									},
									regexp: {
										regexp: /^[\w\-\.\+]+\@[a-zA-Z0-9\.]+\.[a-zA-Z]{2,5}$/,
										message: 'Da 4 a 10 caratteri. (Lettere e numeri accettati)'
									}
								}
							}
						}
					});
		});

$(document)
.ready(
	function() {
		$('#updateUserForm')
			.bootstrapValidator(
				{
					feedback: {
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: {
						nome: {
							container: '#infoNome',
							validators: {
								notEmpty: {
									message: 'Il campo nome non pu&ograve; essere vuoto<br>'
								},
								regexp: {
									regexp: /^[a-zA-Z ,.'-]{2,30}$/,
									message: 'Da 2 a 30 caratteri. (Solo lettere accettate)'
								}
							}
						},
						cognome: {
							container: '#infoCognome',
							validators: {
								notEmpty: {
									message: 'Il campo cognome non pu&ograve; essere vuoto<br>'
								},
								regexp: {
									regexp: /^[a-zA-Z ,.'-]{2,30}$/,
									message: 'Da 2 a 30 caratteri. (Solo lettere accettate)'
								}
							}
						},
						indirizzo: {
							container: '#infoIndirizzo',
							validators: {
								notEmpty: {
									message: 'Il campo indirizzo non pu&ograve; essere vuoto<br>'
								},
								regexp: {
									regexp: /^[a-zA-Z ,.'-]+[0-9]{1,3}$/,
									message: 'Formato indirizzo: (Via | Viale | Piazza, numero civico)'
								}
							}
						},
						cap: {
							container: '#infoCap',
							validators: {
								notEmpty: {
									message: 'Il campo cap non pu&ograve; essere vuoto<br>'
								},
								regexp: {
									regexp: /^[0-9]{5}$/,
									message: 'Inserire 5 cifre per il cap'
								}
							}
						},
						password: {
							container: '#infoPassword',
							validators: {
								notEmpty: {
									message: 'Il campo password non pu&ograve; essere vuoto<br>'
								},
								regexp: {
									regexp: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{4,20}$/,
									message: 'Da 4 a 10 caratteri. (Lettere e numeri accettati, almeno una Maiuscola, una minuscola e un numero)'
								}
							}
						},
						email: {
							container: '#infoEmail',
							validators: {
								notEmpty: {
									message: 'Il campo email non pu&ograve; essere vuoto<br>'
								},
								regexp: {
									regexp: /^[\w\-\.\+]+\@[a-zA-Z0-9\.]+\.[a-zA-Z]{2,5}$/,
									message: 'Da 4 a 10 caratteri. (Lettere e numeri accettati)'
								}
							}
						}
					}
				});
	});