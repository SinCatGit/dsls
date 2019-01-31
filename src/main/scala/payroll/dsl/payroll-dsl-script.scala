import payroll._
import payroll.dsl._
import payroll.dsl.rules._


//val payrollCalculator = rules { employee =>
//  employee salary_for 2.weeks minus_deductions_for { gross =>
//    fedralIncomeTax is (25.0 percent_of gross)
//    stateIncomeTax is (5.0 percent_of gross)
//  }
//
//}


object DlsHello extends  App {
  val payrollCalculator = rules { employee =>
    employee salary_for 2.weeks minus_deductions_for { gross =>
      federalIncomeTax is (25.0 percent_of gross)
      stateIncomeTax is (5.0 percent_of gross)
      insurancePremiums are (500.0 in gross.currency)
      retirementFundContributions are (10.0 percent_of gross)
    }
  }

  val buck = Employee(Name("Buck", "Trends"), Money(80000))
  val jane = Employee(Name("Jane", "Doe"), Money(90000))

  List(buck, jane).foreach { employee =>
    val check = payrollCalculator(employee)
    println("%s %s: %s".format(employee.name.first, employee.name.last, check))
  }

}