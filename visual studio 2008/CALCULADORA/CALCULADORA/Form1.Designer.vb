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
        Me.txt_v1 = New System.Windows.Forms.TextBox
        Me.btn_suma = New System.Windows.Forms.Button
        Me.btn_resta = New System.Windows.Forms.Button
        Me.btn_multiplicacion = New System.Windows.Forms.Button
        Me.btn_division = New System.Windows.Forms.Button
        Me.btn_potencia = New System.Windows.Forms.Button
        Me.btn_raiz = New System.Windows.Forms.Button
        Me.Button7 = New System.Windows.Forms.Button
        Me.btn_borrar = New System.Windows.Forms.Button
        Me.txt_v2 = New System.Windows.Forms.TextBox
        Me.lbl_operador = New System.Windows.Forms.Label
        Me.lbl_res = New System.Windows.Forms.Label
        Me.TextBox3 = New System.Windows.Forms.TextBox
        Me.SuspendLayout()
        '
        'txt_v1
        '
        Me.txt_v1.Location = New System.Drawing.Point(34, 24)
        Me.txt_v1.Name = "txt_v1"
        Me.txt_v1.Size = New System.Drawing.Size(129, 20)
        Me.txt_v1.TabIndex = 0
        '
        'btn_suma
        '
        Me.btn_suma.Font = New System.Drawing.Font("Arial", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_suma.Location = New System.Drawing.Point(27, 89)
        Me.btn_suma.Name = "btn_suma"
        Me.btn_suma.Size = New System.Drawing.Size(49, 35)
        Me.btn_suma.TabIndex = 2
        Me.btn_suma.Text = "+"
        Me.btn_suma.UseVisualStyleBackColor = True
        '
        'btn_resta
        '
        Me.btn_resta.Font = New System.Drawing.Font("Arial", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_resta.Location = New System.Drawing.Point(114, 89)
        Me.btn_resta.Name = "btn_resta"
        Me.btn_resta.Size = New System.Drawing.Size(49, 35)
        Me.btn_resta.TabIndex = 3
        Me.btn_resta.Text = "--"
        Me.btn_resta.UseVisualStyleBackColor = True
        '
        'btn_multiplicacion
        '
        Me.btn_multiplicacion.Font = New System.Drawing.Font("Arial", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_multiplicacion.Location = New System.Drawing.Point(27, 130)
        Me.btn_multiplicacion.Name = "btn_multiplicacion"
        Me.btn_multiplicacion.Size = New System.Drawing.Size(49, 35)
        Me.btn_multiplicacion.TabIndex = 4
        Me.btn_multiplicacion.Text = "*"
        Me.btn_multiplicacion.UseVisualStyleBackColor = True
        '
        'btn_division
        '
        Me.btn_division.Font = New System.Drawing.Font("Arial", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_division.Location = New System.Drawing.Point(114, 130)
        Me.btn_division.Name = "btn_division"
        Me.btn_division.Size = New System.Drawing.Size(49, 35)
        Me.btn_division.TabIndex = 5
        Me.btn_division.Text = "/"
        Me.btn_division.UseVisualStyleBackColor = True
        '
        'btn_potencia
        '
        Me.btn_potencia.Font = New System.Drawing.Font("Arial", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_potencia.Location = New System.Drawing.Point(27, 171)
        Me.btn_potencia.Name = "btn_potencia"
        Me.btn_potencia.Size = New System.Drawing.Size(49, 35)
        Me.btn_potencia.TabIndex = 6
        Me.btn_potencia.Text = "x^"
        Me.btn_potencia.UseVisualStyleBackColor = True
        '
        'btn_raiz
        '
        Me.btn_raiz.Font = New System.Drawing.Font("Arial", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_raiz.Location = New System.Drawing.Point(114, 171)
        Me.btn_raiz.Name = "btn_raiz"
        Me.btn_raiz.Size = New System.Drawing.Size(56, 35)
        Me.btn_raiz.TabIndex = 7
        Me.btn_raiz.Text = "RAIZ"
        Me.btn_raiz.UseVisualStyleBackColor = True
        '
        'Button7
        '
        Me.Button7.Font = New System.Drawing.Font("Arial", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Button7.Location = New System.Drawing.Point(27, 219)
        Me.Button7.Name = "Button7"
        Me.Button7.Size = New System.Drawing.Size(136, 35)
        Me.Button7.TabIndex = 8
        Me.Button7.Text = "="
        Me.Button7.UseVisualStyleBackColor = True
        '
        'btn_borrar
        '
        Me.btn_borrar.Font = New System.Drawing.Font("Arial", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.btn_borrar.Location = New System.Drawing.Point(189, 89)
        Me.btn_borrar.Name = "btn_borrar"
        Me.btn_borrar.Size = New System.Drawing.Size(76, 35)
        Me.btn_borrar.TabIndex = 9
        Me.btn_borrar.Text = "BORRAR"
        Me.btn_borrar.UseVisualStyleBackColor = True
        '
        'txt_v2
        '
        Me.txt_v2.Location = New System.Drawing.Point(215, 24)
        Me.txt_v2.Name = "txt_v2"
        Me.txt_v2.Size = New System.Drawing.Size(128, 20)
        Me.txt_v2.TabIndex = 10
        '
        'lbl_operador
        '
        Me.lbl_operador.AutoSize = True
        Me.lbl_operador.BackColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.lbl_operador.Location = New System.Drawing.Point(170, 27)
        Me.lbl_operador.Name = "lbl_operador"
        Me.lbl_operador.Size = New System.Drawing.Size(39, 13)
        Me.lbl_operador.TabIndex = 11
        Me.lbl_operador.Text = "Label1"
        '
        'lbl_res
        '
        Me.lbl_res.AutoSize = True
        Me.lbl_res.Location = New System.Drawing.Point(350, 28)
        Me.lbl_res.Name = "lbl_res"
        Me.lbl_res.Size = New System.Drawing.Size(39, 13)
        Me.lbl_res.TabIndex = 12
        Me.lbl_res.Text = "Label2"
        '
        'TextBox3
        '
        Me.TextBox3.Location = New System.Drawing.Point(395, 24)
        Me.TextBox3.Name = "TextBox3"
        Me.TextBox3.Size = New System.Drawing.Size(129, 20)
        Me.TextBox3.TabIndex = 13
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(563, 266)
        Me.Controls.Add(Me.TextBox3)
        Me.Controls.Add(Me.lbl_res)
        Me.Controls.Add(Me.lbl_operador)
        Me.Controls.Add(Me.txt_v2)
        Me.Controls.Add(Me.btn_borrar)
        Me.Controls.Add(Me.Button7)
        Me.Controls.Add(Me.btn_raiz)
        Me.Controls.Add(Me.btn_potencia)
        Me.Controls.Add(Me.btn_division)
        Me.Controls.Add(Me.btn_multiplicacion)
        Me.Controls.Add(Me.btn_resta)
        Me.Controls.Add(Me.btn_suma)
        Me.Controls.Add(Me.txt_v1)
        Me.Name = "Form1"
        Me.Text = "CALCULADORA"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents txt_v1 As System.Windows.Forms.TextBox
    Friend WithEvents btn_suma As System.Windows.Forms.Button
    Friend WithEvents btn_resta As System.Windows.Forms.Button
    Friend WithEvents btn_multiplicacion As System.Windows.Forms.Button
    Friend WithEvents btn_division As System.Windows.Forms.Button
    Friend WithEvents btn_potencia As System.Windows.Forms.Button
    Friend WithEvents btn_raiz As System.Windows.Forms.Button
    Friend WithEvents Button7 As System.Windows.Forms.Button
    Friend WithEvents btn_borrar As System.Windows.Forms.Button
    Friend WithEvents txt_v2 As System.Windows.Forms.TextBox
    Friend WithEvents lbl_operador As System.Windows.Forms.Label
    Friend WithEvents lbl_res As System.Windows.Forms.Label
    Friend WithEvents TextBox3 As System.Windows.Forms.TextBox

End Class
