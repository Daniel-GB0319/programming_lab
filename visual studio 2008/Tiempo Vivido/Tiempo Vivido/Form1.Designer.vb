<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class TIEMPO_VIVIDO
    Inherits System.Windows.Forms.Form

    'Form reemplaza a Dispose para limpiar la lista de componentes.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Requerido por el Diseñador de Windows Forms
    Private components As System.ComponentModel.IContainer

    'NOTA: el Diseñador de Windows Forms necesita el siguiente procedimiento
    'Se puede modificar usando el Diseñador de Windows Forms.  
    'No lo modifique con el editor de código.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container
        Me.btn_aceptar = New System.Windows.Forms.Button
        Me.dtp_fnac = New System.Windows.Forms.DateTimePicker
        Me.txt_tvivido = New System.Windows.Forms.RichTextBox
        Me.tim_tvivido = New System.Windows.Forms.Timer(Me.components)
        Me.Label1 = New System.Windows.Forms.Label
        Me.SuspendLayout()
        '
        'btn_aceptar
        '
        Me.btn_aceptar.Location = New System.Drawing.Point(379, 12)
        Me.btn_aceptar.Name = "btn_aceptar"
        Me.btn_aceptar.Size = New System.Drawing.Size(75, 23)
        Me.btn_aceptar.TabIndex = 0
        Me.btn_aceptar.Text = "ACEPTAR"
        Me.btn_aceptar.UseVisualStyleBackColor = True
        '
        'dtp_fnac
        '
        Me.dtp_fnac.Format = System.Windows.Forms.DateTimePickerFormat.[Short]
        Me.dtp_fnac.Location = New System.Drawing.Point(171, 13)
        Me.dtp_fnac.Name = "dtp_fnac"
        Me.dtp_fnac.Size = New System.Drawing.Size(98, 20)
        Me.dtp_fnac.TabIndex = 1
        '
        'txt_tvivido
        '
        Me.txt_tvivido.Location = New System.Drawing.Point(12, 66)
        Me.txt_tvivido.Name = "txt_tvivido"
        Me.txt_tvivido.Size = New System.Drawing.Size(706, 435)
        Me.txt_tvivido.TabIndex = 2
        Me.txt_tvivido.Text = ""
        '
        'tim_tvivido
        '
        Me.tim_tvivido.Interval = 1000
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(9, 17)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(156, 13)
        Me.Label1.TabIndex = 4
        Me.Label1.Text = "Escribe tu fecha de nacimiento:"
        '
        'TIEMPO_VIVIDO
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(462, 314)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.txt_tvivido)
        Me.Controls.Add(Me.dtp_fnac)
        Me.Controls.Add(Me.btn_aceptar)
        Me.Name = "TIEMPO_VIVIDO"
        Me.Text = "Form1"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents btn_aceptar As System.Windows.Forms.Button
    Friend WithEvents dtp_fnac As System.Windows.Forms.DateTimePicker
    Friend WithEvents txt_tvivido As System.Windows.Forms.RichTextBox
    Friend WithEvents tim_tvivido As System.Windows.Forms.Timer
    Friend WithEvents Label1 As System.Windows.Forms.Label

End Class
