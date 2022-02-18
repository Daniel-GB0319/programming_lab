Public Class TIEMPO_VIVIDO
    Public fnac As Date
    Public Años, Decadas, Lustros, Meses, Semanas, Dias, Horas, Minutos, Segundos As Double

    Private Sub btn_aceptar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btn_aceptar.Click
        txt_tvivido.clear()
        tim_tvivido.Enabled = True
    End Sub

    Private Sub tim_tvivido_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tim_tvivido.Tick
        fnac = dtp_fnac.Value
        Años = DateDiff(DateInterval.DayOfYear, fnac, Now) / 365
        Lustros = Años / 5
        Decadas = Años / 10
        Meses = DateDiff(DateInterval.Month, fnac, Now)
        Semanas = DateDiff(DateInterval.WeekOfYear, fnac, Now)
        Dias = DateDiff(DateInterval.DayOfYear, fnac, Now)
        Horas = DateDiff(DateInterval.Hour, fnac, Now)
        Minutos = DateDiff(DateInterval.Minute, fnac, Now)
        Segundos = DateDiff(DateInterval.Second, fnac, Now)

        txt_tvivido.Text = "Has vivido" & Chr(13) & _
        Años.ToString("####.##") & "  Años" & Chr(13) & _
        Lustros.ToString("####.##") & "  Lustros" & Chr(13) & _
        Decadas.ToString("####.##") & "  Decadas" & Chr(13) & _
        Meses.ToString("####.##") & "  Meses" & Chr(13) & _
        Semanas.ToString("####.##") & "  Semanas" & Chr(13) & _
        Dias.ToString("####.##") & "  Dias" & Chr(13) & _
        Horas.ToString("####.##") & "  Horas" & Chr(13) & _
        Minutos.ToString("####.##") & "  Minutos" & Chr(13) & _
        Segundos.ToString("####.##") & "  Segundos y contando"

    End Sub
End Class
