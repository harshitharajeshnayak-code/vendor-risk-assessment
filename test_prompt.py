# load prompt
with open("prompts/describe_prompt.txt", "r") as file:
    prompt_template = file.read()

test_inputs = [
    "Vendor stores customer payment data without encryption",
    "Cloud service provider manages company infrastructure and user data",
    "HR outsourcing firm handles employee personal and payroll records",
    "Logistics vendor tracks shipments and stores delivery information",
    "Marketing agency collects and processes customer behavior data"
]

for i, input_text in enumerate(test_inputs, 1):
    final_prompt = prompt_template.replace("{vendor_input}", input_text)

    print(f"\nTest Case {i}")
    print("=" * 50)
    print("INPUT:", input_text)
    print("PROMPT:\n", final_prompt)