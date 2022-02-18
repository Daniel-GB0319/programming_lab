Public Class Form1 'Daniel Gonzalez B. 3202 NL: 11'

    Private Sub btn_generar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_generar.Click
        Dim n, ntbl As Byte
        ntbl = num_ntbl.Value
        txt_tbl.Text = ""
        For n = 1 To 50
            txt_tbl.Text &= ntbl & ("X") & n & "=" & n * ntbl & Chr(13)
        Next
    End Sub
End Class
