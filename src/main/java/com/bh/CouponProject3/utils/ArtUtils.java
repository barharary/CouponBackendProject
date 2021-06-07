package com.bh.CouponProject3.utils;

import java.util.List;
import java.util.Map;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.beans.Customer;

public class ArtUtils {

	public final static String NEW_LINE = "\n";
	// public final static String BOLD_LINE =
	// "========================================================================";
	public final static String BOLD_LINE = "|***************************************************************************************************************|";
	public final static String REGULAR_LINE = "|--------------------------------------------------------------------------------------------------|";

	
	
	private static int barTestingHeaderCounter = 0;
	public static void barTestingHeader(String input) {
		System.out.println();
		String verticalLines = "///////////////////////////////////////////////////" //
				+ "//////////////////////////////////////////////////////////////";
		String middle = "     " + "Test # " //
				+ (++barTestingHeaderCounter) //
				+ "      " + input + "     ";
		int l = verticalLines.length();
		int m = middle.length();
		String left = "";
		for (int i = 0; i < (l - m) / 2; i++) {
			left += ("/");
		}
		String right = left;
		if ((l - m) % 2 != 0) {
			right += ("/");
		}
		System.out.println();
		System.out.println(verticalLines);
		System.out.print(left);
		System.out.print(middle);
		System.out.println(right);
		System.out.println(verticalLines);
		System.out.println();
	}

	
	
	public static void printPrimaryData() {

		System.out.println("                                            \r\n"
				+ " #####  #####  # #    #   ##   #####  #   # \r\n"
				+ " #    # #    # # ##  ##  #  #  #    #  # #  \r\n"
				+ " #    # #    # # # ## # #    # #    #   #   \r\n"
				+ " #####  #####  # #    # ###### #####    #   \r\n"
				+ " #      #   #  # #    # #    # #   #    #   \r\n"
				+ " #      #    # # #    # #    # #    #   #   \r\n"
				+ "                                            \r\n"
				+ " ######                                     \r\n"
				+ " #     #   ##   #####   ##                  \r\n"
				+ " #     #  #  #    #    #  #                 \r\n"
				+ " #     # #    #   #   #    #                \r\n"
				+ " #     # ######   #   ######                \r\n"
				+ " #     # #    #   #   #    #                \r\n"
				+ " ######  #    #   #   #    #                \r\n" + "                                            ");

	}

	public static void printLoginHeadline() {
		System.out.println("                               \r\n"//
				+ " #       ####   ####  # #    # \r\n" //
				+ " #      #    # #    # # ##   # \r\n" //
				+ " #      #    # #      # # #  # \r\n"//
				+ " #      #    # #  ### # #  # # \r\n"//
				+ " #      #    # #    # # #   ## \r\n"//
				+ " ######  ####   ####  # #    # \r\n"//
				+ "                               ");
	}

	public static void printMainHeadline() {
		System.out.println();
		System.out.println(""//
				+ "\t  #####                                     \r\n" //
				+ "\t #     #   ####   #    #  #####    ####   #    # \r\n"//
				+ "\t #        #    #  #    #  #    #  #    #  ##   # \r\n"
				+ "\t #        #    #  #    #  #    #  #    #  # #  # \r\n"
				+ "\t #        #    #  #    #  #####   #    #  #  # # \r\n"
				+ "\t #     #  #    #  #    #  #       #    #  #   ## \r\n"
				+ "\t  #####    ####    ####   #        ####   #    # \r\n"
				+ "\t                                            \r\n"
				+ "\t  #####                                     \r\n"
				+ "\t #     #  #   #   ####   #####  ######  #    #   \r\n"
				+ "\t #         # #   #         #    #       ##  ##   \r\n"
				+ "\t  #####     #     ####     #    #####   # ## #   \r\n"
				+ "\t       #    #         #    #    #       #    #   \r\n"
				+ "\t #     #    #    #    #    #    #       #    #   \r\n"
				+ "\t  #####     #     ####     #    ######  #    #  ");

	}

	public static void printCompanyHeadline() {
		System.out.println();
		System.out.println("                                                 \r\n"
				+ "  ####   ####  #    # #####    ##   #    # #   # \r\n"
				+ " #    # #    # ##  ## #    #  #  #  ##   #  # #  \r\n"
				+ " #      #    # # ## # #    # #    # # #  #   #   \r\n"
				+ " #      #    # #    # #####  ###### #  # #   #   \r\n"
				+ " #    # #    # #    # #      #    # #   ##   #   \r\n"
				+ "  ####   ####  #    # #      #    # #    #   #   \r\n"
				+ "                                                 ");
	}

	public static void printCustomerHeadline() {
		System.out.println();
		System.out.println("                                                        \r\n"
				+ "  ####  #    #  ####  #####  ####  #    # ###### #####  \r\n"
				+ " #    # #    # #        #   #    # ##  ## #      #    # \r\n"
				+ " #      #    #  ####    #   #    # # ## # #####  #    # \r\n"
				+ " #      #    #      #   #   #    # #    # #      #####  \r\n"
				+ " #    # #    # #    #   #   #    # #    # #      #   #  \r\n"
				+ "  ####   ####   ####    #    ####  #    # ###### #    # \r\n"
				+ "                                                        ");
	}

	public static void printThreadHeadline() {
		System.out.println();
		System.out.println(" ####### #     # ######  #######    #    ######  \r\n"
				+ "    #    #     # #     # #         # #   #     # \r\n"
				+ "    #    #     # #     # #        #   #  #     # \r\n"
				+ "    #    ####### ######  #####   #     # #     # \r\n"
				+ "    #    #     # #   #   #       ####### #     # \r\n"
				+ "    #    #     # #    #  #       #     # #     # \r\n"
				+ "    #    #     # #     # ####### #     # ######  \r\n"
				+ "                                                 ");

	}

	public static void printAdminHeadline() {
		System.out.println();
		System.out.println("                               \r\n" + "   ##   #####  #    # # #    # \r\n"
				+ "  #  #  #    # ##  ## # ##   # \r\n" + " #    # #    # # ## # # # #  # \r\n"
				+ " ###### #    # #    # # #  # # \r\n" + " #    # #    # #    # # #   ## \r\n"
				+ " #    # #####  #    # # #    # \r\n" + "                               ");

	}

	public static void printControllerTest() {

		System.out.println();
		System.out.println(
				"  .oooooo.                             .                      oooo  oooo                     \r\n"
						+ " d8P'  `Y8b                          .o8                      `888  `888                     \r\n"
						+ "888           .ooooo.  ooo. .oo.   .o888oo oooo d8b  .ooooo.   888   888   .ooooo.  oooo d8b \r\n"
						+ "888          d88' `88b `888P\"Y88b    888   `888\"\"8P d88' `88b  888   888  d88' `88b `888\"\"8P \r\n"
						+ "888          888   888  888   888    888    888     888   888  888   888  888ooo888  888     \r\n"
						+ "`88b    ooo  888   888  888   888    888 .  888     888   888  888   888  888    .o  888     \r\n"
						+ " `Y8bood8P'  `Y8bod8P' o888o o888o   \"888\" d888b    `Y8bod8P' o888o o888o `Y8bod8P' d888b    \r\n"
						+ "                                                                                             \r\n"
						+ "                                                                                             \r\n"
						+ "                                                                                             \r\n"
						+ "ooooooooooooo                        .                                                       \r\n"
						+ "8'   888   `8                      .o8                                                       \r\n"
						+ "     888       .ooooo.   .oooo.o .o888oo                                                     \r\n"
						+ "     888      d88' `88b d88(  \"8   888                                                       \r\n"
						+ "     888      888ooo888 `\"Y88b.    888                                                       \r\n"
						+ "     888      888    .o o.  )88b   888 .                                                     \r\n"
						+ "    o888o     `Y8bod8P' 8\"\"888P'   \"888\"                                                     \r\n"
						+ "                                                                                             \r\n"
						+ "                                                                                             \r\n"
						+ "                                                                                             ");

	}

	public static void insertToTable(String headLine, Map<?, ?> map) {
		ArtUtils.printRegularHeadLine(headLine);
		if (map.isEmpty()) {
			System.out.println("| Empty");
		}
		map.entrySet().forEach(entry -> System.out.println("|tokenId: " + entry.getKey() + entry.getValue()));
		System.out.println("| Size of token List: " + map.size());
		System.out.println(ArtUtils.BOLD_LINE);

	}

	public static void insertToTable(String headLine, List<?> list) {
		ArtUtils.printRegularHeadLine(headLine);
		if (list.isEmpty()) {
			System.out.println("Empty");
		} else if (list.get(0) instanceof Company) {
			ArtUtils.companyHeaders();
		} else if (list.get(0) instanceof Customer) {
			ArtUtils.customerHeaders();
		} else if (list.get(0) instanceof Coupon) {
			ArtUtils.couponHeaders();
		}

		list.forEach(System.out::println);
		System.out.println(ArtUtils.BOLD_LINE);

	}

	public static void companyHeaders() {

		System.out.printf("|Id:" + "\t" + "|name:\t\t" + "|Email:\t\t\t\t" + "|Password\t" + "|CouponId:\t\t\t\t|");

		System.out.println(
				"\n|-------|---------------|-------------------------------|---------------|---------------------------------------|");
	}

	public static void customerHeaders() {
		System.out.println(
				"|Id" + "\t" + "|Fisrt  " + "|Last\t" + "|Email\t\t\t" + "|Password\t" + "|CouponId:\t\t\t\t\t|");
		System.out.println(
				"|-------|-------|-------|-----------------------|---------------|-----------------------------------------------|");
	}

	public static void couponHeaders() {

		System.out.println("| Id " + "| Company " + "| Category " + "| Title\t"
				+ "|Description\t|  Start\t|  Expired\t|Amount\t|Price\t|Image\t|" //
				+ "\n|    |   id" + "    |   id     |\t\t|\t\t|   date\t|   date\t|\t|\t|\t|");
		System.out.println(
				"|----|---------|----------|-------------|---------------|---------------|---------------|-------|-------|-------|");
	}

	public static void printRegularHeadLine(String msg) {
		System.out.println(NEW_LINE + BOLD_LINE + NEW_LINE //
				+ "|\t\t\t\t" + msg + "     \t\t\t\t" //
				+ NEW_LINE + BOLD_LINE); //

	}

	public static void printXxsHeadLine(String msg, int num) {
		System.out.println(NEW_LINE + BOLD_LINE + NEW_LINE //
				+ "|\t\t\t" + getX(num) + " " + msg + " " + getX(num) //
				+ NEW_LINE + BOLD_LINE); //

	}

	public static void printVvsHeadLine(String msg, int num) {
		System.out.println(NEW_LINE + BOLD_LINE + NEW_LINE //
				+ "|\t\t\t" + getVi(num) + " " + msg + " " + getVi(num) //
				+ NEW_LINE + BOLD_LINE); //

	}

	public static void CompleteMessage(String msg) throws InterruptedException {
		System.out.println();
		System.out.println("\r\t\t   " + ArtUtils.getVi(3) + msg + ArtUtils.getVi(3));
		Thread.sleep(1500);

	}

	public static void oneLineMessage(String msg) {
		System.out.println();
		System.out.println("\t\t|===============================================|");
		System.out.println("\t\t   " + msg + "\t\t");
		System.out.println("\t\t|===============================================|");
		System.out.println();
	}

	public static String getVi(int num) {
		String msg = "";
		for (int i = 0; i < num; i++) {
			String vi = "\u2714";
			msg = msg + vi;
		}
		return msg;
	}

	public static String getX(int num) {
		String msg = "";
		for (int i = 0; i < num; i++) {
			String vi = "\u2716";
			msg = msg + vi;
		}
		return msg;
	}

	public static String getSmaily() {
		return "\uD83D\uDE0D";

	}

}
