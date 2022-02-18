Public Class Form1

    Private Sub Button8_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_borrar.Click
        v1 = txt_v1.Text = 0
        v2 = txt_v2.Text = 0
        lbl_operador.Text = ""
        lbl_res.Text = 0
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_suma.Click
        v1 = txt_v1.Text
        v2 = txt_v2.Text
        lbl_res.Text = v1 + v2
        lbl_operador.Text = "+"
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_resta.Click
        v1 = txt_v1.Text
        v2 = txt_v2.Text
        lbl_res.Text = v1 - v2
        lbl_operador.Text = "-"
    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_multiplicacion.Click
        v1 = txt_v1.Text
        v2 = txt_v2.Text
        lbl_res.Text = v1 * v2
        lbl_operador.Text = "X"
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_division.Click
        v1 = txt_v1.Text
        v2 = txt_v2.Text
        lbl_res.Text = v1 / v2
        lbl_operador.Text = "/"
    End Sub

    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_potencia.Click
        v1 = txt_v1.Text
        v2 = txt_v2.Text
        lbl_res.Text = v1 * v1
        lbl_operador.Text = "x^"
    End Sub

    Private Sub btn_raiz_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_raiz.Click
        v1 = txt_v1.Text
        v2 = txt_v2.Text
        lbl_res.Text = v1 ^ (1 / v2)
        lbl_operador.Text = "raiz"
    End Sub

    Private Sub txt_v1_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txt_v1.TextChanged

    End Sub
End Class
