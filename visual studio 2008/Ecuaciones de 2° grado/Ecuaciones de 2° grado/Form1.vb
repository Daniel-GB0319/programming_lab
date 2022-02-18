Public Class Form1 'Daniel Gonzalez B. 3202 NL: 11'

    Private Sub btn_calcular_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_calcular.Click
        Dim a, b, c, x1, x2, st As Double
        a = txt_a.Text
        b = txt_b.Text
        c = txt_c.Text

        st = b ^ 2 - (4 * a * c)

        If st > 0 Then
            x1 = (-b + Math.Sqrt(st)) / (2 * a)
            x2 = (-b - Math.Sqrt(st)) / (2 * a)
            txt_res.Text = x1.ToString("####") & Chr(13) & x2.ToString("####")

        Else
            MsgBox("Lo siento, la ecuacion no tiene solucion en numeros reales", 64)

        End If
    End Sub
End Class
