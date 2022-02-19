export interface Loan {
    loanCode?: string;
    loanValue: number;
    fisrtDateToPayment: Date;
    quantityOfPayment: number;
    clientId?: number;
    canceledLoan?: boolean;
    dateCanceledLoan?: Date;
}