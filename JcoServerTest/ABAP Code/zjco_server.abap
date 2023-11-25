
report  zjco_test1.

data: requtext like sy-lisel,
      resp_text like sy-lisel,
      echo_text like sy-lisel.

data: rfcdest like rfcdes-rfcdest value 'NONE'.
data: rfc_mess(128).

requtext = '你好，JCO3服务器！'.
rfcdest = 'JCOSERVER01'. " corresponds to the destination name defined in SM59

write: 'Call function STFC_CONNECTION using ', rfcdest.

call function 'STFC_CONNECTION' destination rfcdest
  exporting
    requtext = requtext
  importing
    echotext = echo_text
    resptext = resp_text.

if sy-subrc is initial.
  write: / 'Call STFC_CONNECTION'.
  write: / resp_text.
endif.