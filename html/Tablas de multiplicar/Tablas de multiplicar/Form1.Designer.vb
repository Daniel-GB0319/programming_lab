<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form1
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
        Me.num_ntbl = New System.Windows.Forms.NumericUpDown
        Me.btn_generar = New System.Windows.Forms.Button
        Me.txt_tbl = New System.Windows.Forms.RichTextBox
        CType(Me.num_ntbl, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'num_ntbl
        '
        Me.num_ntbl.Location = New System.Drawing.Point(12, 12)
        Me.num_ntbl.Name = "num_ntbl"
        Me.num_ntbl.Size = New System.Drawing.Size(120, 20)
        Me.num_ntbl.TabIndex = 0
        '
        'btn_generar
        '
        Me.btn_generar.Location = New System.Drawing.Point(239, 12)
        Me.btn_generar.Name = "btn_generar"
        Me.btn_generar.Size = New System.Drawing.Size(75, 23)
        Me.btn_generar.TabIndex = 1
        Me.btn_generar.Text = "GENERAR"
        Me.btn_generar.UseVisualStyleBackColor = True
        '
        'txt_tbl
        '
        Me.txt_tbl.Location = New System.Drawing.Point(12, 41)
        Me.txt_tbl.Name = "txt_tbl"
        Me.txt_tbl.Size = New System.Drawing.Size(414, 681)
        Me.txt_tbl.TabIndex = 2
        Me.txt_tbl.Text = ""
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(438, 734)
        Me.Controls.Add(Me.txt_tbl)
        Me.Controls.Add(Me.btn_generar)
        Me.Controls.Add(Me.num_ntbl)
        Me.Name = "Form1"
        Me.Text = "TABLAS DE MULTIPLICAR"
        CType(Me.num_ntbl, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents num_ntbl As System.Windows.Forms.NumericUpDown
    Friend WithEvents btn_generar As System.Windows.Forms.Button
    Friend WithEvents txt_tbl As System.Windows.Forms.RichTextBox

End Class
